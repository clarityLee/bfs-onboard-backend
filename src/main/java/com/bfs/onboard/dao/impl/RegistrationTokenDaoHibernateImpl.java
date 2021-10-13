package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.RegistrationTokenDao;
import com.bfs.onboard.domain.RegistrationToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class RegistrationTokenDaoHibernateImpl extends BasicTemplate implements RegistrationTokenDao {

    @Override
    public void save(RegistrationToken registrationToken) {
        super.save(registrationToken);
    }

    @Override
    public boolean validate(String email, String token, LocalDateTime now) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RegistrationToken> cq = builder.createQuery(RegistrationToken.class);
        Root<RegistrationToken> root = cq.from(RegistrationToken.class);
        cq.select(root).where(
                builder.equal(root.get("email"), email),
                builder.equal(root.get("token"), token),
                builder.lessThan(root.get("validduration"), LocalDateTime.now())
        );
        List<RegistrationToken> rTokens = session.createQuery(cq).getResultList();
        return rTokens.size() == 1;
    }

    @Override
    public RegistrationToken findByToken(String token) {
        List<RegistrationToken> results = getByField("token", token, RegistrationToken.class);
        return results.isEmpty() ? new RegistrationToken() : results.get(0);
    }
}
