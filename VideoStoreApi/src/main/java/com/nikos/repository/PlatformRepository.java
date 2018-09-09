package com.nikos.repository;

import com.nikos.model.Platform;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class PlatformRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String SelectSQL = "SELECT PlatformID, PlatformDescription, Developers FROM Platform {WHERE} {ORDER}";

    private String KeyColumn = "PlatformID";

    private String SelectSQLForVG = "SELECT PlatformID, " +
            "PlatformDescription, " +
            "Developers " +
            "FROM Platform " +
            "INNER JOIN VideoGame_Platform " +
            "ON Platform.PlatformID = VideoGame_Platform.platforms_PlatformID " +
            "{WHERE} {ORDER}";

    private String KeyColumnForVG = "VideoGame_Platform.VideoGame_MediaID";

    public ArrayList<Platform> getPlatformList(){

        try {
            String findQuery = SelectSQL
                    .replace("{WHERE}", "")
                    .replace("{ORDER}", "ORDER BY PlatformDescription");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(findQuery).addEntity(Platform.class);
            List<Platform> result = searchQuery.getResultList();
            transaction.commit();
            return new ArrayList<>(result);
        }
        catch(Exception ex){
            return null;
        }
    }

    public HashSet<Platform> getPlatformListForVideoGame(Session currentSession, int VideoGameID){

        try {
            String findQuery = SelectSQLForVG
                    .replace("{WHERE}", "WHERE " + KeyColumnForVG + " = " + VideoGameID)
                    .replace("{ORDER}", "");
            Query listQuery = currentSession.createSQLQuery(findQuery).addEntity(Platform.class);
            List<Platform> result = listQuery.getResultList();
            return new HashSet<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public Platform insertNewPlatform(Platform newPlatform) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newPlatform);
            transaction.commit();
            newPlatform.setPlatformID(NewID);
            return newPlatform;
        }
        catch (Exception ex){
            return null;
        }
    }

    public Platform updateSelectedPlatform(Platform platform){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(platform);
            transaction.commit();
            return platform;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedPlatform(int PlatformID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query deletePlatforms = session.createNativeQuery("DELETE FROM VideoGame_Platform WHERE platforms_PlatformID = ?");
            deletePlatforms.setParameter(1, PlatformID);
            deletePlatforms.executeUpdate();

            Query deleteQuery = session.createQuery("DELETE FROM Platform WHERE PlatformID = :platformID");
            deleteQuery.setParameter("platformID", PlatformID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return PlatformID;
        }
        catch(Exception ex){
            return 0;
        }
    }
}
