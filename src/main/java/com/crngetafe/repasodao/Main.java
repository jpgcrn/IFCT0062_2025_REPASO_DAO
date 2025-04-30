package com.crngetafe.repasodao;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;
import com.crngetafe.repasodao.persistence.IMovieDAO;
import com.crngetafe.repasodao.persistence.MovieDAOFactory;
import com.crngetafe.repasodao.util.PropertiesReader;

import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        IMovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
        if (movieDAO == null) {
            System.out.println("Error al elegir gestor de BBDD!");
            exit(-1);
        }
        Movie elconclave = new Movie("El cónclave", "Desconocido");
        Movie indianajones = new Movie("Indiana Jones", "Steven Spielberg");
        try {
            int id = movieDAO.create(elconclave);
            if (id > 0) {
                System.out.println("Película creada con id " + id);
            }
            Movie peliculaLeida = movieDAO.read(id);
            System.out.println(peliculaLeida);

            id = movieDAO.create(indianajones);
            if (id > 0) {
                System.out.println("Película creada con id " + id);
            }
            peliculaLeida = movieDAO.read(id);
            System.out.println(peliculaLeida);

            List<Movie> allMovies = movieDAO.readAll();
            //for (int i = 0; i < allMovies.toArray().length; i++) {
            //    System.out.println(allMovies.get(i).toString());
            //}
            allMovies.forEach(movie -> {
                System.out.println(movie);
            });
            //Otra forma:
            //allMovies.forEach(System.out::println);
        }
        catch (PersistenceException pex) {
            System.err.println(pex.getMessage());
        }
    }
}