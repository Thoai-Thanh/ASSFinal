/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class Movie {
    private String nameMovie;
    private String genre;
    private double ticketPrice;
    private String roomID;
    private String country;

    public Movie(String nameMovie, String genre, double ticketPrice, String roomID, String country) {
        this.nameMovie = nameMovie;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
        this.roomID = roomID;
        this.country = country;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
