package com.nikos.repository;

import com.nikos.model.MovieCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieCategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String SelectSQL = "SELECT MovieCategoryID, " +
            "MovieCategoryName " +
            "FROM MovieCategory {WHERE} {ORDER}";

    private String SelectSQLForMovies = "SELECT MovieCategoryID, " +
            "MovieCategoryName " +
            "FROM MovieCategory " +
            "INNER JOIN Movie_MovieCategory " +
            "ON MovieCategory.MovieCategoryID = Movie_MovieCategory.movieCategories_MovieCategoryID " +
            "{WHERE} {ORDER}";

    private String KeyColumn = "MovieCategoryID";

    private String KeyColumnForMovies = "Movie_MovieCategory.Movie_MediaID";

    public ArrayList<MovieCategory> getMovieCategoryList(){

        try {
            String findQuery = SelectSQL
                    .replace("{WHERE}", "")
                    .replace("{ORDER}", "ORDER BY MovieCategoryName");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(findQuery).addEntity(MovieCategory.class);
            List<MovieCategory> result = searchQuery.getResultList();
            transaction.commit();
            return new ArrayList<>(result);
        }
        catch(Exception ex){
            return null;
        }
    }

    public HashSet<MovieCategory> getMovieCategoriesByMovieID(Session currentSession, int MovieID){

        try {
            String findQuery = SelectSQLForMovies
                    .replace("{WHERE}", "WHERE " + KeyColumnForMovies + " = " + MovieID)
                    .replace("{ORDER}", "");
            Query searchQuery = currentSession.createSQLQuery(findQuery).addEntity(MovieCategory.class);
            List<MovieCategory> result = searchQuery.getResultList();
            return new HashSet<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieCategory insertNewMovieCategory(MovieCategory newMovieCategory) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMovieCategory);
            transaction.commit();
            newMovieCategory.setMovieCategoryID(NewID);
            return newMovieCategory;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MovieCategory updateSelectedMovieCategory(MovieCategory movieCategory){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(movieCategory);
            transaction.commit();
            return movieCategory;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMovieCategory(int MovieCategoryID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            org.hibernate.query.Query deleteMovieCategories = session.createNativeQuery("DELETE FROM Movie_MovieCategory WHERE Movie_MovieCategory.movieCategories_MovieCategoryID = ?");
            deleteMovieCategories.setParameter(1, MovieCategoryID);
            deleteMovieCategories.executeUpdate();

            org.hibernate.query.Query deleteQuery = session.createQuery("DELETE FROM MovieCategory WHERE MovieCategoryID = :categoryID");
            deleteQuery.setParameter("categoryID", MovieCategoryID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return MovieCategoryID;
        }
        catch(Exception ex){
            return 0;
        }
    }
}
