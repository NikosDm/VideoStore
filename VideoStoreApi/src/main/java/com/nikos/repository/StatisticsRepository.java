package com.nikos.repository;

import com.nikos.model.dto.MediaStatistics;
import com.nikos.model.dto.UserRoleStatistics;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public MediaStatistics loadMediaStatistics() {
        try {
            String SelectSQL = "SELECT * " +
                    "FROM vMediaStatistics";
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query selectQuery = session.createSQLQuery(SelectSQL);
            MediaStatistics mediaStatistics = (MediaStatistics) selectQuery.setResultTransformer(Transformers.aliasToBean(MediaStatistics.class)).getSingleResult();
            transaction.commit();
            return mediaStatistics;
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<UserRoleStatistics> loadUserStatistics(){
        try {
            String SelectSQL = "SELECT * " +
                    "FROM vUserRoleStatistics";
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query selectQuery = session.createSQLQuery(SelectSQL);
            List<UserRoleStatistics> userRoleStatistics = selectQuery.setResultTransformer(Transformers.aliasToBean(UserRoleStatistics.class)).getResultList();
            transaction.commit();
            return userRoleStatistics;
        }
        catch (Exception ex){
            return null;
        }
    }
}
