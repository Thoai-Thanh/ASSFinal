/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class Customer extends Person {

    private static List<Customer> customerList = new ArrayList<>();
    private String address;
    private int age;
    private double balance;
    private List<Movie> bookedMovies;
    private Manager manager;

    public Customer() {
        this.balance = 0.0;
        this.bookedMovies = new ArrayList<>();
    }

    public Customer(String username, String password, String fullName, int age, String phone, String address, Manager manager) {
        super(username, password, fullName, phone);
        this.address = address;
        this.age = age;
        this.balance = 0.0;
        this.bookedMovies = new ArrayList<>();
        this.manager = manager;
    }

    // in ra thông tin của khách hàng
    @Override
    public void displayInfo() {
        System.out.println("\n=== Customer Information ===");
        System.out.println("Username: " + username);
        System.out.println("Full name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }

    // nạp tiền vào tài khoản 
    public void addFunds(Scanner sc) {
        System.out.print("Enter amount to add ($): ");
        try {
            // nhập số tiền nạp
            double amount = Double.parseDouble(sc.nextLine());
            if (amount > 0) {
                this.balance += amount;
                System.out.println("Successfully added $" + String.format("%.2f", amount));
                // số dư mới
                System.out.println("New balance: $" + String.format("%.2f", this.balance));
            } else {
                System.out.println("Please enter a positive amount!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount! Please enter a valid number.");
        }
    }

    // tìm kiếm phim
    public void searchMovies(Scanner sc) {
        if (manager == null || manager.getMovies().isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

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
                    // lấy cái danh sách phim bên manager tạo 1 stream 
                    // dùng filter để lọc mấy cái trùng tên xong gòi thu thập vào collectors
                    results = manager.getMovies().stream()
                            .filter(m -> m.getNameMovie().toLowerCase().contains(searchTerm))
                            .collect(Collectors.toList());
                    break;
                case 2:
                    results = manager.getMovies().stream()
                            .filter(m -> m.getGenre().toLowerCase().contains(searchTerm))
                            .collect(Collectors.toList());
                    break;
                case 3:
                    results = manager.getMovies().stream()
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
                displayMovieList(results);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    // đặt vé phim
    public void bookTicket(Scanner sc) {
        if (manager == null || manager.getMovies().isEmpty()) {
            System.out.println("No movies available for booking.");
            return;
        }

        System.out.println("\n=== Available Movies ===");
        displayMovieList(manager.getMovies());

        System.out.print("Enter the movie name you want to book: ");
        String movieName = sc.nextLine();
        // tạo 1 stream từ danh sách phim
        Movie selectedMovie = manager.getMovies().stream()
                .filter(m -> m.getNameMovie().equalsIgnoreCase(movieName))
                .findFirst()
                .orElse(null);

        if (selectedMovie == null) {
            System.out.println("Movie not found!");
            return;
        }

        if (balance < selectedMovie.getTicketPrice()) {
            System.out.println("Insufficient balance! Please add funds.");
            System.out.println("Required: $" + String.format("%.2f", selectedMovie.getTicketPrice()));
            System.out.println("Your balance: $" + String.format("%.2f", balance));
            return;
        }

        balance -= selectedMovie.getTicketPrice();
        bookedMovies.add(selectedMovie);
        System.out.println("\nBooking successful!");
        manager.setTotalRevenue(manager.getTotalRevenue() + selectedMovie.getTicketPrice());
        manager.setTotalTicketsSold(manager.getTotalTicketsSold() + 1);
        System.out.println("Movie: " + selectedMovie.getNameMovie());
        System.out.println("Room: " + selectedMovie.getRoomID());
        System.out.println("Price: $" + String.format("%.2f", selectedMovie.getTicketPrice()));
        System.out.println("Remaining balance: $" + String.format("%.2f", balance));
    }

    private void displayMovieList(List<Movie> movies) {
        System.out.println("+-----------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-11s | %-10s |\n",
                "No.", "Movie Name", "Genre", "Room", "Price($)", "Country");
        System.out.println("+-----------------------------------------------------------------------------------------+");

        int count = 1;
        for (Movie movie : movies) {
            System.out.printf("| %-5d | %-20s | %-15s | %-10s | %-11.2f | %-10s |\n",
                    count++,
                    movie.getNameMovie(),
                    movie.getGenre(),
                    movie.getRoomID(),
                    movie.getTicketPrice(),
                    movie.getCountry());
        }
        System.out.println("+-----------------------------------------------------------------------------------------+");
    }

    public void viewBookingHistory() {
        if (bookedMovies.isEmpty()) {
            System.out.println("No booking history.");
            return;
        }

        System.out.println("\n=== Booking History ===");
        displayMovieList(bookedMovies);
    }

    @Override
    public void displayMenu(Scanner sc) {
        while (true) {
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. View Movie List");
            System.out.println("2. Search Movies");
            System.out.println("3. Add Funds");
            System.out.println("4. Book Ticket");
            System.out.println("5. View Booking History");
            System.out.println("6. View My Profile");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        if (manager != null) {
                            displayMovieList(manager.getMovies());
                        } else {
                            System.out.println("No movies available.");
                        }
                        break;
                    case 2:
                        searchMovies(sc);
                        break;
                    case 3:
                        addFunds(sc);
                        break;
                    case 4:
                        bookTicket(sc);
                        break;
                    case 5:
                        viewBookingHistory();
                        break;
                    case 6:
                        displayInfo();
                        break;
                    case 7:
                        System.out.println("Thank you for using our service!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Movie> getBookedMovies() {
        return bookedMovies;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
