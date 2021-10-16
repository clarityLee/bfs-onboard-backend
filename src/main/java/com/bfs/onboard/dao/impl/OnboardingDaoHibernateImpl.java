package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.OnboardingDao;
import com.bfs.onboard.domain.response.OnboardingAppDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class OnboardingDaoHibernateImpl implements OnboardingDao {

    private final static String ONBOARDING_APPLICATIONS =
            "SELECT e.id, p.id, a.id, " +
            "p.firstName, p.lastName, v.visaType, e.visaStartDate, e.visaEndDate, a.status " +
            "FROM ApplicationWorkFlow a " +
            "INNER JOIN a.employee e " +
            "INNER JOIN e.person p " +
            "LEFT JOIN e.visaStatus v " +
            "ORDER BY a.modificationDate desc";

    SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OnboardingAppDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<OnboardingAppDto> onboardingAppDtos = session
                .createQuery(ONBOARDING_APPLICATIONS)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(
                        new ResultTransformer() {
                            @Override
                            public Object transformTuple(Object[] objects, String[] strings) {
                                return new OnboardingAppDto(
                                        (Integer) objects[0],
                                        (Integer) objects[1],
                                        (Integer) objects[2],
                                        (String) objects[3],
                                        (String) objects[4],
                                        (String) objects[5],
                                        (LocalDate) objects[6],
                                        (LocalDate) objects[7],
                                        (Integer) objects[8]
                                );
                            }

                            @Override
                            public List transformList(List list) {
                                return list;
                            }
                        }
                )
                .getResultList();

        return onboardingAppDtos;
    }

    public void example() {
        Integer userId = 15;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User u where u.id = :userId")
                .setParameter("userId", userId);
    }
}
