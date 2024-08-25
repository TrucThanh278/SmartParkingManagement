package com.ou.repository.impl;

import com.ou.pojo.VerificationToken;
import com.ou.repository.VerificationTokenRepository;
import com.ou.service.impl.UserServiceImpl;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {
        private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public VerificationToken findByToken(String token) {
        try {
            TypedQuery<VerificationToken> query = entityManager.createQuery(
                    "SELECT vt FROM VerificationToken vt WHERE vt.token = :token", VerificationToken.class);
            query.setParameter("token", token);
            query.getSingleResult().getUser().setEnabled(true);
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warning("No verification token found for token: " + token);
            return null;
        }
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        if (verificationToken.getId() == null) {
            entityManager.persist(verificationToken);
            return verificationToken;
        } else {
            return entityManager.merge(verificationToken);
        }
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        if (entityManager.contains(verificationToken)) {
            entityManager.remove(verificationToken);
        } else {
            VerificationToken attached = entityManager.find(VerificationToken.class, verificationToken.getId());
            if (attached != null) {
                entityManager.remove(attached);
            }
        }
    }
}

