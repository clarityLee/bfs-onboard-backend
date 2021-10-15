package com.bfs.onboard.dao;

import com.bfs.onboard.domain.UnclaimedFile;

public interface UnclaimedFileDao {
    void deleteByPath(String path);
}
