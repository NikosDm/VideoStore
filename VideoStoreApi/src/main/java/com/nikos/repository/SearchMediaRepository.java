package com.nikos.repository;

import com.nikos.model.SearchMedia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchMediaRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String SelectSQL = "SELECT * " +
            "FROM vMediaSearch " +
            "WHERE LOWER(MediaCode) LIKE LOWER('%{WHERE_PARAM}%') OR LOWER(MediaTitle) LIKE LOWER('%{WHERE_PARAM}%') " +
            "ORDER BY {ORDER_PARAM} ASC";

    public ArrayList<SearchMedia> getMediaList(String WhereClause, String OrderClause){

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String FindQuery = SelectSQL.replace("{WHERE_PARAM}", WhereClause);
            if (OrderClause.isEmpty()){
                FindQuery = FindQuery.replace("{ORDER_PARAM}", "MediaTitle");
            }
            else {
                FindQuery = FindQuery.replace("{ORDER_PARAM}", OrderClause);
            }
            Query searchMediaQuery = session.createSQLQuery(FindQuery).addEntity(SearchMedia.class);
            List<SearchMedia> searchMedias = searchMediaQuery.getResultList();
            transaction.commit();
            return new ArrayList<>(searchMedias);
        }
        catch (Exception ex){
            return null;
        }
    }
}
