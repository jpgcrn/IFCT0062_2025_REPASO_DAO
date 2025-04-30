package com.crngetafe.repasodao.model;

import jakarta.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="director")
    private String director;

    public Movie() {

    }

    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }

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
