package com.crngetafe.repasodao.persistence.hibernate;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;
import com.crngetafe.repasodao.persistence.IMovieDAO;
import org.hibernate.Session;

import java.util.List;

public class MovieDAOImplHibernate implements IMovieDAO {
    @Override
    public int create(Movie movie) throws PersistenceException {
        int newId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(movie);

        Movie m = session.get(Movie.class, movie.getId());
        newId = m.getId();

        session.getTransaction().commit();
        session.close();
        
        return newId;
    }

    @Override
    public Movie read(int id) throws PersistenceException {
        return null;
    }

    @Override
    public List<Movie> readAll() throws PersistenceException {
        return List.of();
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void delete(int id) {

    }
}
