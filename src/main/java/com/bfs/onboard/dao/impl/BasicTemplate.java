package com.bfs.onboard.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class BasicTemplate {

    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public <T> void templateSave(T e) {
//        sessionFactory.getCurrentSession().save(e);
//    }
    public <T> void save(T e) {
        sessionFactory.getCurrentSession().save(e);
    }

    <T> List<T> getAll(Class<T> c) {
        return getAllAndFetch(c, "");
    }

    <T> List<T> getAllAndFetch(Class<T> c, String attribute) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(c);
        Root<T> root = cq.from(c);
        if (!attribute.isEmpty())
            root.fetch(attribute, JoinType.LEFT);
        cq.select(root);
        return session.createQuery(cq).getResultList();
    }

    <T, K> List<T> getByField(String attrName, K attr, Class<T> c) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(c);
        Root<T> root = cq.from(c);
        cq.select(root).where(builder.equal(root.get(attrName), attr));
        return session.createQuery(cq).getResultList();
    }

}
