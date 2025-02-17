/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class Manager extends Person {

    private List<Movie> movie;
    private double totalRevenue;
    private int totalTicketsSold;

    public Manager(String username, String password, String fullName, String phone) {
        super(username, password, fullName, phone);
        this.movie = new ArrayList<>();
        movie.add(new Movie("Lam", "Action", 15.99, "A1", "VN"));
        movie.add(new Movie("Thoai", "Animation", 12.99, "B2", "Japan"));
        movie.add(new Movie("Thanh", "Drama", 13.99, "C3", "Korea"));
    }

    @Override
    public void displayInfo() {
        System.out.println("\n=== Manager Information ===");
        System.out.println("Manager: " + username);
        System.out.println("Full Name: " + fullName);
        System.out.println("Phone: " + phone);
    }

    @Override
    public void displayMenu(Scanner sc) {
        while (true) {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1. Display Movie List");
            System.out.println("2. Add New Movie");
            System.out.println("3. Remove Movie");
            System.out.println("4. Edit Movie");
            System.out.println("5. Search Movie");
            System.out.println("6. View Manager Info");
            System.out.println("7. View Revenue and Tickets Sold");
            System.out.println("8. logout");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        displayMovieList();
                        break;
                    case 2:
                        addMovieWithInput(sc);
                        break;
                    case 3:
                        removeMovie(sc);
                        break;
                    case 4:
                        editMovieWithInput(sc);
                        break;
                    case 5:
                        searchMovie(sc);
                        break;
                    case 6:
                        displayInfo();
                        break;
                    case 7:
                        viewRevenueAndTicketsSold();
                        break;
                    case 8:
                        System.out.println("Logging out from manager account...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private void addMovieWithInput(Scanner sc) {
        System.out.println("\n=== Add New Movie ===");
        System.out.print("Enter movie name: ");
        String name = sc.nextLine();

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        double price = 0;
        while (true) {
            try {
                System.out.print("Enter ticket price: $");
                price = Double.parseDouble(sc.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("Price must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid price!");
            }
        }

        System.out.print("Enter room ID: ");
        String roomID = sc.nextLine();

        System.out.print("Enter country: ");
        String country = sc.nextLine();

        addMovie(name, genre, price, roomID, country);
    }
    // thêm phim

    public void addMovie(String name, String genre, double price, String roomID, String country) {
        // dùng anymatch để kiểm tra xem có trùng tên không
        if (movie.stream().anyMatch(m -> m.getNameMovie().equalsIgnoreCase(name))) {
            System.out.println("Movie with this name already exists!");
            return;
        }
        movie.add(new Movie(name, genre, price, roomID, country));
        System.out.println("Movie added successfully!");
    }
    // xóa phim

    public void removeMovie(Scanner sc) {
        System.out.println("\n=== Remove Movie ===");
        System.out.print("Enter movie name to remove: ");
        String name = sc.nextLine();
        // dùng iterator để duyệt qua danh sách phim
        Iterator<Movie> movieIterator = movie.iterator();
        boolean found = false;
        // vòng lặp kiểm tra xem còn phim nào tiếp theo không
        while (movieIterator.hasNext()) {
            // lấy phim tiếp theo
            Movie mv = movieIterator.next();
            if (mv.getNameMovie().equalsIgnoreCase(name)) {
                // xóa
                movieIterator.remove();
                System.out.println("Movie '" + name + "' has been removed.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Movie not found!");
        }
    }
    // chỉnh sửa phim

    private void editMovieWithInput(Scanner sc) {
        System.out.println("\n=== Edit Movie ===");
        System.out.print("Enter movie name to edit: ");
        String name = sc.nextLine();
        // tạo stream từ danh sách tìm tên đầu tiên hiện ra khi nhập
        Movie movieToEdit = movie.stream()
                .filter(m -> m.getNameMovie().equalsIgnoreCase(name)).findFirst().orElse(null);

        if (movieToEdit == null) {
            System.out.println("Movie not found!");
            return;
        }

        System.out.println("Current movie details:");
        System.out.println("Name: " + movieToEdit.getNameMovie());
        System.out.println("Genre: " + movieToEdit.getGenre());
        System.out.println("Price: $" + movieToEdit.getTicketPrice());
        System.out.println("Room: " + movieToEdit.getRoomID());
        System.out.println("Country: " + movieToEdit.getCountry());

        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("New genre [" + movieToEdit.getGenre() + "]: ");
        String newGenre = sc.nextLine();
        if (!newGenre.isEmpty()) {
            movieToEdit.setGenre(newGenre);
        }

        System.out.print("New price [$" + movieToEdit.getTicketPrice() + "]: ");
        String newPriceStr = sc.nextLine();
        if (!newPriceStr.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(newPriceStr);
                if (newPrice > 0) {
                    movieToEdit.setTicketPrice(newPrice);
                } else {
                    System.out.println("Price must be greater than 0! Keeping current price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format! Keeping current price.");
            }
        }

        System.out.print("New room ID [" + movieToEdit.getRoomID() + "]: ");
        String newRoomID = sc.nextLine();
        if (!newRoomID.isEmpty()) {
            movieToEdit.setRoomID(newRoomID);
        }

        System.out.print("New country [" + movieToEdit.getCountry() + "]: ");
        String newCountry = sc.nextLine();
        if (!newCountry.isEmpty()) {
            movieToEdit.setCountry(newCountry);
        }

        System.out.println("Movie updated successfully!");
    }
    // tìm phim

    private void searchMovie(Scanner sc) {
        System.out.println("\n=== Search Movies ===");
        System.out.println("1. Search by name");
        System.out.println("2. Search by genre");
        System.out.println("3. Search by country");
        System.out.print("Enter your choice: ");

        try {
            int choice = Integer.parseInt(sc.nextLine());
            System.out.print("Enter search term: ");
            String searchTerm = sc.nextLine().toLowerCase();

            List<Movie> results = new ArrayList<>();
            switch (choice) {
                case 1:
                    results = movie.stream()
                            .filter(m -> m.getNameMovie().toLowerCase().contains(searchTerm))
                            .collect(Collectors.toList());
                    break;
                case 2:
                    results = movie.stream()
                            .filter(m -> m.getGenre().toLowerCase().contains(searchTerm))
                            .collect(Collectors.toList());
                    break;
                case 3:
                    results = movie.stream()
                            .filter(m -> m.getCountry().toLowerCase().contains(searchTerm))
                            .collect(Collectors.toList());
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            if (results.isEmpty()) {
                System.out.println("No movies found matching your search.");
            } else {
                System.out.println("\nSearch results:");
                displayMovieList(results);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }
    // hiển thị phim

    private void displayMovieList(List<Movie> movieList) {
        if (movieList.isEmpty()) {
            System.out.println("+-----------------------+");
            System.out.println("|   No movies found.    |");
            System.out.println("+-----------------------+");
            return;
        }

        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-11s | %-10s |\n",
                "No.", "Movie Name", "Genre", "Room", "Price($)", "Country");
        System.out.println("+----------------------------------------------------------------------------------------+");

        int count = 1;
        for (Movie mv : movieList) {
            System.out.printf("| %-5d | %-20s | %-15s | %-10s | %-11.2f | %-10s |\n",
                    count++,
                    mv.getNameMovie(),
                    mv.getGenre(),
                    mv.getRoomID(),
                    mv.getTicketPrice(),
                    mv.getCountry());
            System.out.println("+----------------------------------------------------------------------------------------+");
        }
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public void setTotalTicketsSold(int totalTicketsSold) {
        this.totalTicketsSold = totalTicketsSold;
    }
    // kiểm tra doanh thu và vé đã bán
    public void viewRevenueAndTicketsSold() {
        System.out.println("\n=== Revenue and Tickets Sold ===");
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println("Total Tickets Sold: " + totalTicketsSold);
    }

    public void displayMovieList() {
        displayMovieList(movie);
    }

    public List<Movie> getMovies() {
        return movie;
    }
}
