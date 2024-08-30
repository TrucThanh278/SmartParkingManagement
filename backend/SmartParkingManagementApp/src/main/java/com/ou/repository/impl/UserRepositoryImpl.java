package com.ou.repository.impl;

import com.ou.repository.UserRepository;
import com.ou.pojo.User;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query<User> query = session.createQuery(
                    "SELECT u FROM User u LEFT JOIN FETCH u.roleId WHERE u.username = :username",
                    User.class
            );
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return u != null && this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        return u;
    }

    @Override
    public User save(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
        return user;
    }

    @Override
    public boolean userExistsByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        String query = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
        TypedQuery<Long> typedQuery = s.createQuery(query, Long.class);
        typedQuery.setParameter("username", username);
        Long count = typedQuery.getSingleResult();
        return count > 0;
    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void enableUser(User u) {
        u.setEnabled(true);
    }
}
