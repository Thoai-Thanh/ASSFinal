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

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class Manager extends Person {

    private List<Movie> movie;

    public Manager(String username, String password, String fullName, String phone) {
        super(username, password, fullName, phone);
        this.movie = new ArrayList<>();
        Movie mv = new Movie("Thanh", "Hoat Hinh", 100, "01", "VN");
        movie.add(mv);
    }

    @Override
    public void displayInfo() {
        System.out.println("Manager: " + username);
    }

    public void addMovie(String name, String genre, double price, String roomID, String country) {
        movie.add(new Movie(name, genre, price, roomID, country));
        System.out.println("Movie added successfully!");
    }

    public void removeMovie(Scanner sc) {
        System.out.print("nhập tên phim muốn xóa: ");
        String name = sc.nextLine();
        Iterator<Movie> phim = movie.iterator();
        // phim dc sd để duyệt qua các obj movie
        // iterator(); trả về đối tượng Iterator<Movie>
        // lấy một iterator cho danh sách phim (m) và lưu trữ nó trong biến phimIterator.
        while (phim.hasNext()) {
            //hasnext là true nếu còn phần tử tiếp theo trong danh sách 
            Movie mv = phim.next();
            //lấy đối tượng Movie tiếp theo từ iterator và lưu trữ nó trong biến movie.
            if (mv.getNameMovie().equalsIgnoreCase(name)) {
                phim.remove();
                System.out.println("đã xóa phim: " + name);
                return;
            }
            if (movie.isEmpty()) {
                System.out.println("Danh sách phim trống");
            }
        }
        System.out.println("Movie removed successfully!");
    }

    public void displayMovieList() {
        if (movie.isEmpty()) {
            System.out.println("+-----------------------+");
            System.out.println("| Danh sách phim trống. |");
            System.out.println("+-----------------------+");
            return;
        }

        System.out.println("+-----------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-11s | %-10s |\n", "STT", "Tên phim", "Thể loại", "Phòng", "Giá", "Quốc Gia");
        System.out.println("+-----------------------------------------------------------------------------------------+");

        int stt = 1;
        for (Movie phim : movie) {
            System.out.printf("| %-5d | %-20s | %-15s | %-10s | %-11.2f | -%-10s |\n",
                    stt, phim.getNameMovie(), phim.getGenre(), phim.getRoomID(), phim.getTicketPrice(), phim.getCountry());
            stt++;
            System.out.println("+-----------------------------------------------------------------------------------------+");
        }
    }

    public void editMovie(String name, double newPrice, String roomID) {
        for (Movie movie : movie) {
            if (movie.getNameMovie().equals(name)) {
                movie.setTicketPrice(newPrice);
                movie.setRoomID(roomID);
                System.out.println("Movie updated successfully!");
                return;
            }
        }
        System.out.println("Movie not found!");
    }

    public List<Movie> getMovies() {
        return movie;
    }
}
