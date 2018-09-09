package com.nikos.repository;

import com.nikos.model.Media;
import com.nikos.model.VideoGame;
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
public class VideoGameRepository implements MediaRepository{

    private String SelectSQL = "SELECT Media.MediaID, " +
            "Media.MediaCode, " +
            "Media.MediaTitle, " +
            "Media.MediaDescription, " +
            "Media.Price, " +
            "Media.ReleaseDate, " +
            "Media.MediaImage, " +
            "VideoGame.VideoGameMedia, " +
            "VideoGame.Manufacturer, " +
            "VideoGame.Publisher, " +
            "VideoGame.Developers " +
            "FROM Media " +
            "INNER JOIN VideoGame ON Media.MediaID = VideoGame.MediaID {WHERE} {ORDER}";

    private String KeyColumn = "Media.MediaID";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PlatformRepository platformRepository;

    public VideoGame insertNewMediaData(Media newMediaData) {

        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMediaData);
            transaction.commit();
            newMediaData.setMediaID(NewID);
            return (VideoGame)newMediaData;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<VideoGame> getMediaData(String WhereClause, String OrderClause) {

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", WhereClause).replace("{ORDER}", OrderClause);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(VideoGame.class);
            List<VideoGame> result = searchQuery.getResultList();

            for (VideoGame videoGame: result){
                videoGame.setPlatforms(platformRepository.getPlatformListForVideoGame(session, videoGame.getMediaID()));
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

            Query deletePlatforms = session.createNativeQuery("DELETE FROM VideoGame_Platform WHERE VideoGame_MediaID = ?");
            deletePlatforms.setParameter(1, MediaID);
            deletePlatforms.executeUpdate();

            Query deleteVideoGameQuery = session.createQuery("DELETE FROM Media WHERE MediaID = :mediaID");
            deleteVideoGameQuery.setParameter("mediaID", MediaID);
            deleteVideoGameQuery.executeUpdate();

            transaction.commit();
            return MediaID;
        }
        catch(Exception ex){
            return 0;
        }
    }

    public VideoGame updateMediaData(Media mediaData) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(mediaData);
            transaction.commit();
            return (VideoGame)mediaData;
        }
        catch (Exception ex){
            return null;
        }
    }

    public VideoGame findMediaByMediaID(int MediaID) {

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "WHERE " + KeyColumn + " = " + MediaID).replace("{ORDER}", "");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(VideoGame.class);
            VideoGame result = (VideoGame)searchQuery.uniqueResult();
            result.setPlatforms(platformRepository.getPlatformListForVideoGame(session, MediaID));
            transaction.commit();
            return result;
        }
        catch(Exception ex){
            return null;
        }
    }
}
