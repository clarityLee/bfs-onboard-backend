package com.bfs.onboard.dao;

import com.bfs.onboard.domain.User;

public interface UserDao {
    boolean exist(String username);
    User findByName(String username);
    User fetchDetail(Integer userId);
    User findByPersonId(Integer personId);
}
