package com.nikos.repository;

import com.nikos.model.StoreUserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreUserRoleRepository {

    private String SelectSQL = "SELECT RoleID, RoleName FROM StoreUserRole {WHERE} {ORDER}";

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public StoreUserRole getStoreUserAdminRole() {
        try{
            String FindQuery = SelectSQL.replace("{WHERE}", "WHERE RoleName = 'Admin'").replace("{ORDER}", "");
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(StoreUserRole.class);
            StoreUserRole result = (StoreUserRole)searchQuery.getSingleResult();
            transaction.commit();
            return result;
        }
        catch(Exception e){
            return null;
        }
    }

    public StoreUserRole getStoreUserRoleByUserID(int UserID){

        try{
            String FindQuery = "Select TOP 1 RoleID, RoleName " +
                    "FROM StoreUserRole " +
                    "INNER JOIN StoreUser_StoreUserRole " +
                    "ON StoreUser_StoreUserRole.storeUserRoleList_RoleID = StoreUserRole.RoleID " +
                    "WHERE StoreUser_StoreUserRole.StoreUser_UserID = " + UserID;
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(StoreUserRole.class);
            StoreUserRole result = (StoreUserRole)searchQuery.getSingleResult();
            transaction.commit();
            return result;
        }
        catch(Exception ex){
            return null;
        }
    }

    public ArrayList<StoreUserRole> getStoreUserRoleList(String username) {
        try {
            String FindQuery = "Select RoleID, RoleName " +
                    "FROM StoreUserRole " +
                    "INNER JOIN StoreUser_StoreUserRole " +
                    "ON StoreUser_StoreUserRole.storeUserRoleList_RoleID = StoreUserRole.RoleID " +
                    "INNER JOIN StoreUser " +
                    "ON StoreUser_StoreUserRole.StoreUser_UserID = StoreUser.UserID " +
                    "WHERE StoreUser.Username != '" + username + "'";
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query searchQuery = session.createSQLQuery(FindQuery).addEntity(StoreUserRole.class);
            List<StoreUserRole> result = searchQuery.getResultList();
            transaction.commit();
            return new ArrayList<>(result);
        }
        catch (Exception ex){
            return null;
        }
    }

    public StoreUserRole insertNewStoreUserRole(StoreUserRole newStoreUserRole) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer NewID = (Integer)session.save(newStoreUserRole);
            transaction.commit();
            newStoreUserRole.setRoleID(NewID);
            return newStoreUserRole;
        }
        catch (Exception ex){
            return null;
        }
    }

    public StoreUserRole updateSelectedStoreUserRole(StoreUserRole storeUserRole){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(storeUserRole);
            transaction.commit();
            return storeUserRole;
        }
        catch (Exception ex){
            return null;
        }
    }

    public int deleteSelectedStoreUserRole(int StoreUserRoleID) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            org.hibernate.query.Query deleteUserRoles = session.createNativeQuery("DELETE FROM StoreUser_StoreUserRole WHERE StoreUser_StoreUserRole.storeUserRoleList_RoleID = ?");
            deleteUserRoles.setParameter(1, StoreUserRoleID);
            deleteUserRoles.executeUpdate();

            org.hibernate.query.Query deleteQuery = session.createQuery("DELETE FROM StoreUserRole WHERE RoleID = :roleID");
            deleteQuery.setParameter("roleID", StoreUserRoleID);
            deleteQuery.executeUpdate();

            transaction.commit();
            return StoreUserRoleID;
        }
        catch(Exception ex){
            return 0;
        }
    }
}
