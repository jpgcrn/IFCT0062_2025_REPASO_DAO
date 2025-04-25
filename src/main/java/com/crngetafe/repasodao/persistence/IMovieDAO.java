package com.crngetafe.repasodao.persistence;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;

import java.util.List;

public interface IMovieDAO {
    int create(Movie movie) throws PersistenceException;

    Movie read(int id) throws PersistenceException;

    List<Movie> readAll() throws PersistenceException;

    void update(Movie movie);

    void delete(int id);
}
