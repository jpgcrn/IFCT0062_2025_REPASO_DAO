package com.crngetafe.repasodao.model;

public class Movie {
    private int id;
    private String title;
    private String director;

    public Movie(int id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return "Datos: Movie{id=" + this.id + ", title='" + this.title + "', director='" + this.director + "'}";
    }

    public void setId(int id) {
        this.id = id;
    }
}
