package com.bfs.onboard.dao;

import com.bfs.onboard.domain.Role;

public interface RoleDao {
    Role findByRole(String rolename);
}
