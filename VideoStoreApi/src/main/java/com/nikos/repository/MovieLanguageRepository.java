package com.nikos.repository;

import com.nikos.model.MovieLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieLanguageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String SelectSQL = "SELECT MovieLanguageID, " +
            "MovieLanguageName " +
            "FROM MovieLanguage {WHERE} {ORDER}";

    private String SelectSQLForMovies = "SELECT MovieLanguageID, " +
            "MovieLanguageName " +
            "FROM MovieLanguage " +
            "INNER JOIN Movie_MovieLanguage " +
            "ON MovieLanguage.MovieLanguageID = Movie_MovieLanguage.movieLanguages_MovieLanguageID " +
            "{WHERE} {ORDER}";

    private String KeyColumn = "MovieLanguageID";

    private String KeyColumnForMovies = "Movie_MovieLanguage.Movie_MediaID";

    public ArrayList<MovieLanguage> GetMovieLanguagesList(){

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "").replace("{ORDER}", "ORDER BY MovieLanguageName");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(FindQuery).addEntity(MovieLanguage.class);
            List<MovieLanguage> movieLanguages = query.getResultList();
            transaction.commit();
            return new ArrayList<>(movieLanguages);
        }
        catch(Exception ex){
            return null;
        }
    }

    public HashSet<MovieLanguage> getMovieLanguagesByMovieID(Session currentSession, int MovieID){

        try {
            String findQuery = SelectSQLForMovies
                    .replace("{WHERE}", "WHERE " + KeyColumnForMovies + " = " + MovieID)
                    .replace("{ORDER}", "");
            Query searchQuery = currentSession.createSQLQuery(findQuery).addEntity(MovieLanguage.class);
            List<MovieLanguage> result = searchQuery.getResultList();
            return new HashSet<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieLanguage insertNewMovieLanguage(MovieLanguage newMovieLanguage) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMovieLanguage);
            transaction.commit();
            newMovieLanguage.setMovieLanguageID(NewID);
            return newMovieLanguage;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieLanguage updateSelectedMovieLanguage(MovieLanguage movieLanguage){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(movieLanguage);
            transaction.commit();
            return movieLanguage;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMovieLanguage(int MovieLanguageID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            org.hibernate.query.Query deleteMovieLanguages = session.createNativeQuery("DELETE FROM Movie_MovieLanguage WHERE Movie_MovieLanguage.movieLanguages_MovieLanguageID = ?");
            deleteMovieLanguages.setParameter(1, MovieLanguageID);
            deleteMovieLanguages.executeUpdate();

            org.hibernate.query.Query deleteQuery = session.createQuery("DELETE FROM MovieLanguage WHERE MovieLanguageID = :movieLanguageID");
            deleteQuery.setParameter("movieLanguageID", MovieLanguageID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return MovieLanguageID;
        }
        catch(Exception ex){
            return 0;
        }
    }
}
