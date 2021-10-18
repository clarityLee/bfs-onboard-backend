package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.UnclaimedFileDao;
import com.bfs.onboard.domain.UnclaimedFile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UnclaimedFileDaoHibernateImpl extends BasicTemplate implements UnclaimedFileDao {

    private final static String DELETE_BY_PATH = "delete from UnclaimedFile where path= :path";

    @Override
    public void deleteByPath(String path) {
        sessionFactory.getCurrentSession().createQuery(DELETE_BY_PATH)
                .setParameter("path", path).executeUpdate();
    }

}
