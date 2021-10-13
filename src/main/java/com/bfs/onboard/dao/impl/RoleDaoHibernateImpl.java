package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.RoleDao;
import com.bfs.onboard.domain.Role;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDaoHibernateImpl extends BasicTemplate implements RoleDao {

    @Override
    public Role findByRole(String roleName) {
        List<Role> res = getByField("roleName", roleName, Role.class);
        return res.isEmpty() ? new Role() : res.get(0);
    }
}
