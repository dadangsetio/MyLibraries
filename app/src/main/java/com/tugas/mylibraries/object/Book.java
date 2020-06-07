package com.tugas.mylibraries.object;

public class Book {
    private int id_Books, Year, Pages, Rating;
    private String Title, Author, Publisher, Language;

    public Book(){

    }

    public Book(int id, String title, String author, String publisher, String language, int year, int pages, int rating){
        this.id_Books = id;
        this.Title = title;
        this.Author = author;
        this.Publisher = publisher;
        this.Language = language;
        this.Year = year;
        this.Pages = pages;
        this.Rating = rating;
    }
    public int getId_Books() {
        return id_Books;
    }

    public void setId_Books(int id_Books) {
        this.id_Books = id_Books;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int pages) {
        Pages = pages;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
