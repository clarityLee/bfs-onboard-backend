package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.UnclaimedFileDao;
import com.bfs.onboard.domain.UnclaimedFile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UnclaimedFileDaoHibernateImpl extends BasicTemplate implements UnclaimedFileDao {

    @Override
    public void save(UnclaimedFile unclaimedFile) {
        super.save(unclaimedFile);
    }

    @Override
    public void delete(UnclaimedFile unclaimedFile) {
        super.delete(unclaimedFile);
    }
}
