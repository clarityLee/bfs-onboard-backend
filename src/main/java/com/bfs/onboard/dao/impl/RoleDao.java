package com.bfs.onboard.dao.impl;

import com.bfs.onboard.domain.Role;

public interface RoleDao {
    Role findByRole(String rolename);
}
