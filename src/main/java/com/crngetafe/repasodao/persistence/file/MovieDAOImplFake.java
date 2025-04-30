package com.crngetafe.repasodao.persistence.file;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;
import com.crngetafe.repasodao.persistence.IMovieDAO;

import java.util.ArrayList;
import java.util.List;

public class MovieDAOImplFake implements IMovieDAO {
    private List<Movie> movies = new ArrayList();

    public int create(Movie movie) throws PersistenceException {
        this.movies.add(movie);
        return movie.getId();
    }

    public Movie read(int id) throws PersistenceException {
        return (Movie) this.movies.stream().filter((movie) -> movie.getId() == id).findFirst().get();
    }

    public List<Movie> readAll() throws PersistenceException {
        return this.movies;
    }

    public void update(Movie movie) {
    }

    public void delete(int id) {
    }
}