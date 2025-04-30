package com.crngetafe.repasodao.persistence;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImplPostgre implements IMovieDAO {
    private static final String dbURL = "jdbc:postgresql://localhost:5432/movies";
    private static final String dbUser = "user_app";
    private static final String dbPassword = "password";

    @Override
    public int create(Movie movie) throws PersistenceException {
        int auto_id = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "INSERT INTO movie(title, director) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getDirector());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
                auto_id = rs.getInt(1);
        } catch (SQLException sqlex) {
            throw new PersistenceException(sqlex.getMessage());
        }

        return auto_id;
    }

    @Override
    public Movie read(int id) throws PersistenceException {
        Movie retVal = null;
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM movie WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                retVal = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"));
            }
        }
        catch (SQLException sqlex) {
            throw new PersistenceException(sqlex.getMessage());
        }
        return retVal;
    }

    @Override
    public List<Movie> readAll() throws PersistenceException {
        List<Movie> retVal = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM movie";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                retVal.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director")));
            }
        }
        catch (SQLException sqlex) {
            throw new PersistenceException(sqlex.getMessage());
        }
        return retVal;
    }

    @Override
    public void update(Movie movie) {
        //TODO Pendiente
    }

    @Override
    public void delete(int id) {
        //TODO Pendiente
    }
}
