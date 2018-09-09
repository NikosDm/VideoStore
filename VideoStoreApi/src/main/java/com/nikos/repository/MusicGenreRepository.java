package com.nikos.repository;

import com.nikos.model.MusicGenre;
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
public class MusicGenreRepository {

    private String SelectSQL = "SELECT GenreID, " +
            "GenreDescription " +
            "FROM MusicGenre {WHERE} {ORDER}";

    private String SelectSQLForMusicDisks = "SELECT GenreID, " +
            "GenreDescription " +
            "FROM MusicGenre " +
            "INNER JOIN MusicDisk_MusicGenre " +
            "ON MusicGenre.GenreID = MusicDisk_MusicGenre.musicGenres_GenreID " +
            "{WHERE} {ORDER}";

    private String KeyColumn = "GenreID";

    private String KeyColumnForMusicDisks = "MusicDisk_MusicGenre.MusicDisk_MediaID";

    @Autowired
    private SessionFactory sessionFactory;

    public ArrayList<MusicGenre> GetMusicGenresList(){

        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "").replace("{ORDER}", "ORDER BY GenreDescription");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(FindQuery).addEntity(MusicGenre.class);
            List<MusicGenre> musicGenres = query.getResultList();
            transaction.commit();
            return new ArrayList<>(musicGenres);
        }
        catch(Exception ex){
            return null;
        }
    }

    public HashSet<MusicGenre> getMusicGenresByMusicDiskID(Session currentSession, int MusicDiskID){

        try {
            String findQuery = SelectSQLForMusicDisks
                    .replace("{WHERE}", "WHERE " + KeyColumnForMusicDisks + " = " + MusicDiskID)
                    .replace("{ORDER}", "");
            Query searchQuery = currentSession.createSQLQuery(findQuery).addEntity(MusicGenre.class);
            List<MusicGenre> result = searchQuery.getResultList();
            return new HashSet<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicGenre insertNewMusicGenre(MusicGenre newMusicGenre) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newMusicGenre);
            transaction.commit();
            newMusicGenre.setGenreID(NewID);
            return newMusicGenre;
        }
        catch (Exception ex){
            return null;
        }
    }

    public MusicGenre updateSelectedMusicGenre(MusicGenre musicGenre){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(musicGenre);
            transaction.commit();
            return musicGenre;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedMusicGenre(int MusicGenreID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            org.hibernate.query.Query deleteMusicGenres = session.createNativeQuery("DELETE FROM MusicDisk_MusicGenre WHERE musicGenres_GenreID = ?");
            deleteMusicGenres.setParameter(1, MusicGenreID);
            deleteMusicGenres.executeUpdate();

            org.hibernate.query.Query deleteQuery = session.createQuery("DELETE FROM MusicGenre WHERE GenreID = :genreID");
            deleteQuery.setParameter("genreID", MusicGenreID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return MusicGenreID;
        }
        catch(Exception ex){
            return 0;
        }
    }
}
