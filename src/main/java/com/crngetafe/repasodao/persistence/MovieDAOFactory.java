package com.crngetafe.repasodao.persistence;

import com.crngetafe.repasodao.util.PropertiesReader;

public class MovieDAOFactory {
    public static IMovieDAO getMovieDAO() {
//        IMovieDAO retVal = null;
        Object persistenceImplObj = null;
        String tipoPersistencia = "";
        try {
            tipoPersistencia = PropertiesReader.getProperty("persistence.type");
            Class persistenceImplCls = Class.forName(tipoPersistencia);
            persistenceImplObj = persistenceImplCls.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        switch (tipoPersistencia) {
//            case "postgre":
//                retVal = new MovieDAOImplPostgre();
//                break;
//            case "sqlite":
//                retVal = new MovieDAOImplSQLite();
//                break;
//            case "memory":
//                retVal = new MovieDAOImplFake();
//                break;
//        }
//        return retVal;
        return (IMovieDAO) persistenceImplObj;
    }
}
