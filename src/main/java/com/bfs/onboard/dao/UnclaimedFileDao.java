package com.bfs.onboard.dao;

import com.bfs.onboard.domain.UnclaimedFile;

public interface UnclaimedFileDao {

    void save(UnclaimedFile unclaimedFile);
    void delete(UnclaimedFile unclaimedFile);

}
