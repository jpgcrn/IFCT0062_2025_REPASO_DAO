package com.crngetafe.repasodao.persistence;

public class MovieDAOFactory {
    public static IMovieDAO getMovieDAO() {
        return new MovieDAOImplPostgre(); //∫ MovieDAOImplSQLite(); //MovieDAOImplFake();
    }
}
