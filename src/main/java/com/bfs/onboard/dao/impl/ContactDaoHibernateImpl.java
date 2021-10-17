package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.ContactDao;
import com.bfs.onboard.domain.Contact;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ContactDaoHibernateImpl extends BasicTemplate implements ContactDao {
    @Override
    public List<Contact> fetchListByOwner(Integer ownerId) {
        Session session = sessionFactory.getCurrentSession();
        List<Contact> contacts = findByFieldAndFetch(
                "ownerId", ownerId, "person", Contact.class);
        for (Contact c : contacts) {
            Hibernate.initialize(c.getPerson().getAddresses());
        }
        return contacts;
    }
}
