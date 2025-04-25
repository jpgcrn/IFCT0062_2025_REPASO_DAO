package com.crngetafe.repasodao.persistence;

import com.crngetafe.repasodao.exceptions.PersistenceException;
import com.crngetafe.repasodao.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImplSQLite implements IMovieDAO {
    private static final String dbURL = "jdbc:sqlite:/Users/tardes/IdeaProjects/IFCT0062_2025_REPASO_DAO/movies.db";
    private static final String dbUser = "";
    private static final String dbPassword = "";

    @Override
    public int create(Movie movie) throws PersistenceException {
        int auto_id = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "INSERT INTO movies(title, director) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getDirector());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
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
            String sql = "SELECT * FROM movies WHERE id = ?";
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
            String sql = "SELECT * FROM movies";
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

    }

    @Override
    public void delete(int id) {

    }
}
