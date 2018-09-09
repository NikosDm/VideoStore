package com.nikos.repository;

import com.nikos.model.Media;
import com.nikos.model.MusicDisk;
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
public class MusicDiskRepository implements MediaRepository {

    private String SelectSQL = "SELECT Media.MediaID, " +
            "Media.MediaCode, " +
            "Media.MediaTitle, " +
            "Media.MediaDescription, " +
            "Media.Price, " +
            "Media.ReleaseDate, " +
            "Media.MediaImage, " +
            "MusicDisk.Musician " +
            "FROM Media " +
            "INNER JOIN MusicDisk ON Media.MediaID = MusicDisk.MediaID {WHERE} {ORDER}";

    private String KeyColumn = "Media.MediaID";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MusicGenreRepository musicGenreRepository;

    public MusicDisk findMediaByMediaID(int MediaID) {
        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "WHERE " + KeyColumn + " = " + MediaID).replace("{ORDER}", "");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(MusicDisk.class);
            MusicDisk result = (MusicDisk)searchQuery.uniqueResult();
            result.setMusicGenres(musicGenreRepository.getMusicGenresByMusicDiskID(session, result.getMediaID()));
            transaction.commit();
            return result;
        }
        catch (Exception ex){
            return null;
        }
    }

    public ArrayList<MusicDisk> getMediaData(String WhereClause, String OrderClause) {
        try{
            String FindQuery = SelectSQL.replace("{WHERE}", WhereClause).replace("{ORDER}", OrderClause);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(MusicDisk.class);
            List<MusicDisk> result = searchQuery.getResultList();

            for (MusicDisk musicDisk: result){
                musicDisk.setMusicGenres(musicGenreRepository.getMusicGenresByMusicDiskID(session, musicDisk.getMediaID()));
            }

            transaction.commit();
            return new ArrayList<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicDisk updateMediaData(Media mediaData) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(mediaData);
            transaction.commit();
            return (MusicDisk)mediaData;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteMedia(int MediaID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query deleteMusicGenres = session.createNativeQuery("DELETE FROM MusicDisk_MusicGenre WHERE MusicDisk_MediaID = ?");
            deleteMusicGenres.setParameter(1, MediaID);
            deleteMusicGenres.executeUpdate();

            Query deleteMusicDisk = session.createQuery("DELETE FROM Media WHERE MediaID = :mediaID");
            deleteMusicDisk.setParameter("mediaID", MediaID);
            deleteMusicDisk.executeUpdate();

            transaction.commit();
            return MediaID;
        }
        catch(Exception ex){
            return 0;
        }
    }

    public MusicDisk insertNewMediaData(Media newMediaData) {

        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMediaData);
            transaction.commit();
            newMediaData.setMediaID(NewID);
            return (MusicDisk)newMediaData;
        }
        catch (Exception ex){
            return null;
        }
    }
}
