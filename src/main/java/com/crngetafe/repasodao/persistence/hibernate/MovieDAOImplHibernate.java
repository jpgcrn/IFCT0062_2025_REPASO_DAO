package com.crngetafe.repasodao.persistence.hibernate;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;
import com.crngetafe.repasodao.persistence.IMovieDAO;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Movie movie = session.find(Movie.class, id);
        session.close();
        return movie;
    }

    @Override
    public List<Movie> readAll() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Query query = session.createQuery("FROM Movie");
        //Lo anterior está obsoleto, se podría hacer así:
        TypedQuery<Movie> query = session.createQuery("FROM Movie", Movie.class);
        List<Movie> movies = query.getResultList();
        session.close();
        return movies;
    }

    @Override
    public void update(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        Movie m = session.find(Movie.class, movie.id);
//        session.evict(m);
//        m.setTitle(movie.getTitle());
//        m.setDirector(movie.getDirector());
        session.merge(movie);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Movie m = session.get(Movie.class, id);
        session.remove(m);

        session.getTransaction().commit();
        session.close();
    }
}
