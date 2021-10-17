package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.domain.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl extends BasicTemplate implements UserDao {

    @Override
    public boolean exist(String username) {
        List<User> results = getByField("username", username, User.class);
        return results.size() == 1;
    }

    @Override
    public User findByName(String username) {
        List<User> result = getByField("username", username, User.class);
        return result.size() == 1 ? result.get(0) : new User();
    }

    @Override
    public User fetchDetail(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = findById(userId, User.class);
        Hibernate.initialize(user.getPerson());
        Hibernate.initialize(user.getPerson().getAddresses());
        Hibernate.initialize(user.getPerson().getEmployee());
        Hibernate.initialize(user.getPerson().getEmployee().getVisaStatus());
        Hibernate.initialize(user.getPerson().getEmployee().getApplicationWorkFlow());
        return user;
    }

    @Override
    public User findByPersonId(Integer personId) {
        List<User> res = getByField("personId", personId, User.class);
        return res.size() == 1 ? res.get(0) : new User();
    }
}
