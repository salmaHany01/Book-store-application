package com.example.bsp3admoon;
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double price;
    private double Ratings;
    private int quantity;

    public Book ()
    {

    }
    public Book (String title, String Author, String genre, double price, double ratings, int id, int quantity)
    {
        this.title= title;
        this.author=Author;
        this.id= id;
        this.genre= genre;
        this.price= price;
        this.Ratings= ratings;
        this.quantity = quantity;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRatings() {
        return Ratings;
    }

    public void setRatings(double ratings) {
        Ratings = ratings;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
