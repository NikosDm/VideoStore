package com.nikos.repository;

import com.nikos.model.StoreUser;
import com.nikos.model.StoreUserRole;
import org.bouncycastle.util.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreUserRepository {

    private String SelectSQL = "SELECT UserID, " +
                "Username, " +
                "Password, " +
                "FirstName, " +
                "LastName, " +
                "CreateDate, " +
                "Email," +
                "UserImage FROM StoreUser {WHERE} {ORDER}";

    private String KeyColumn = "UserID";

    @Autowired
    private SessionFactory sessionFactory;

    public StoreUser insertNewData(StoreUser data) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(data);
            data.setUserID(NewID.intValue());
            transaction.commit();
            return data;
        }
        catch(Exception ex){
            return null;
        }
    }

    public ArrayList<StoreUser> getData(String WhereClause){
        try {
            String FindQuery = SelectSQL.replace("{WHERE}", WhereClause).replace("{ORDER}", "");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(StoreUser.class);
            List<StoreUser> result = searchQuery.getResultList();
            transaction.commit();
            return new ArrayList<>(result);
        }
        catch(Exception ex){
            return null;
        }
    }

    public StoreUser updateStoreUserDetails(StoreUser storeUser){
        try {
            String UpdateSQL = "UPDATE StoreUser SET " +
                                    "FirstName = :firstName, " +
                                    "LastName = :lastName, " +
                                    "Email = :email, " +
                                    "UserImage = :userImage " +
                                "WHERE UserID = :userID";

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query updateQuery = session.createNativeQuery(UpdateSQL).addEntity(StoreUser.class);
            updateQuery.setParameter("firstName", storeUser.getFirstName());
            updateQuery.setParameter("lastName", storeUser.getLastName());
            updateQuery.setParameter("email", storeUser.getEmail());
            updateQuery.setParameter("userImage", storeUser.getUserImage());
            updateQuery.setParameter("userID", storeUser.getUserID());

            updateQuery.executeUpdate();
            transaction.commit();
            return storeUser;
        }
        catch (Exception ex){
            return null;
        }
    }
}
