package com.tugas.mylibraries.object;

public class Game {
    private int id_Game, Size, Rating;
    private String Name, Developer, Category;

    public int getId_Game() {
        return id_Game;
    }

    public void setId_Game(int id_Game) {
        this.id_Game = id_Game;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDeveloper() {
        return Developer;
    }

    public void setDeveloper(String developer) {
        Developer = developer;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
