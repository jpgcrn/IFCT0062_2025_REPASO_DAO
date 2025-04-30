package com.crngetafe.repasodao.persistence;

public class MovieDAOImplSQLite extends MovieDAOImplSQL {
    public MovieDAOImplSQLite() {
        dbURL = "jdbc:sqlite:/Users/tardes/IdeaProjects/IFCT0062_2025_REPASO_DAO/movies.db";
        dbUser = "";
        dbPassword = "";
    }
}
