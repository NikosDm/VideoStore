package com.nikos.repository;

import com.nikos.model.Media;
import com.nikos.model.Movie;
import com.nikos.repository.interfaces.MediaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository implements MediaRepository{

    private String SelectSQL = "SELECT Media.MediaID, " +
            "Media.MediaCode, " +
            "Media.MediaTitle, " +
            "Media.MediaDescription, " +
            "Media.Price, " +
            "Media.ReleaseDate, " +
            "Media.MediaImage, " +
            "Movie.Director " +
            "FROM Media " +
            "INNER JOIN Movie ON Media.MediaID = Movie.MediaID {WHERE} {ORDER}";

    private String KeyColumn = "Media.MediaID";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    @Autowired
    private MovieLanguageRepository movieLanguageRepository;

    public Movie insertNewMediaData(Media newMediaData) {

        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMediaData);
            transaction.commit();
            newMediaData.setMediaID(NewID);
            return (Movie)newMediaData;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<Movie> getMediaData(String WhereClause, String OrderClause) {

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", WhereClause).replace("{ORDER}", OrderClause);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(Movie.class);
            List<Movie> result = searchQuery.getResultList();

            for (Movie movie: result){

                movie.setMovieCategories(movieCategoryRepository.getMovieCategoriesByMovieID(session, movie.getMediaID()));
                movie.setMovieLanguage(movieLanguageRepository.getMovieLanguagesByMovieID(session, movie.getMediaID()));
            }

            transaction.commit();
            return new ArrayList<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteMedia(int MediaID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query deleteCategoriesQuery = session.createNativeQuery("DELETE FROM Movie_MovieCategory WHERE Movie_MediaID = ?");
            deleteCategoriesQuery.setParameter(1, MediaID);
            deleteCategoriesQuery.executeUpdate();

            Query deleteLanguagesQuery = session.createNativeQuery("DELETE FROM Movie_MovieLanguage WHERE Movie_MediaID = ?");
            deleteLanguagesQuery.setParameter(1, MediaID);
            deleteLanguagesQuery.executeUpdate();

            Query deleteQuery = session.createQuery("DELETE FROM Media WHERE MediaID = :mediaID");
            deleteQuery.setParameter("mediaID", MediaID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return MediaID;
        }
        catch(Exception ex){
            return 0;
        }
    }

    public Movie updateMediaData(Media mediaData) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(mediaData);
            transaction.commit();
            return (Movie)mediaData;
        }
        catch (Exception ex){
            return null;
        }
    }

    public Movie findMediaByMediaID(int MediaID) {

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "WHERE " + KeyColumn + " = " + MediaID).replace("{ORDER}", "");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(Movie.class);
            Movie result = (Movie)searchQuery.uniqueResult();
            result.setMovieLanguage(movieLanguageRepository.getMovieLanguagesByMovieID(session, MediaID));
            result.setMovieCategories(movieCategoryRepository.getMovieCategoriesByMovieID(session, MediaID));
            transaction.commit();
            return result;
        }
        catch(Exception ex){
            return null;
        }
    }
}
