package com.bfs.onboard.dao;

import com.bfs.onboard.domain.User;

public interface UserDao {
    void save(User user);
    boolean exist(String username);
    User findByName(String username);
}
