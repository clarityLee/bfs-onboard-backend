package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.domain.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl extends BasicTemplate implements UserDao {

    @Override
    public void save(User user) {
        super.save(user);
//        templateSave(user);
    }

    @Override
    public boolean exist(String username) {
        List<User> results = getByField("username", username, User.class);
        return results.size() == 1 ? true : false;
    }
}
