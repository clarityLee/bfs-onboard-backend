package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.UserRoleDao;
import com.bfs.onboard.domain.UserRole;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRoleDaoHibernateImpl extends BasicTemplate implements UserRoleDao {

    @Override
    public void save(UserRole userRole) {
        super.save(userRole);
    }
}
