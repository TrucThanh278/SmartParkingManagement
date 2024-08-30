/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trucn
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From User");
        return q.getResultList();
    }

    @Override
    public User getUserDetail(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        Predicate idPredicate = b.equal(root.get("id"), id);
        q.where(idPredicate);

        Query query = s.createQuery(q);
        return (User) query.getSingleResult();
    }

//    @Override
//    public User getUserByEmail(String email) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Query q = s.createNamedQuery("User.findByEmail");
//        q.setParameter("email", email);
//        
//        return (User) q.getSingleResult();
//    }
    
    @Override
    public void deleteUser(Integer id){
        Session s = this.factory.getObject().getCurrentSession();
        User u = s.get(User.class, id);
        s.delete(u);
    }
    
//    @Override
//    public boolean authUser(String email, String password) {
//        User  u = this.getUserByEmail(email);
//        return this.passEncoder.matches(password, u.getPassword());
//    }
    
     @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u); 
        return u;
    }
    
    public User getUserByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        
        return (User) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return u != null && this.passEncoder.matches(password, u.getPassword());
    }

//    @Override
//    public User addUser(User u) {
//        Session s = this.factory.getObject().getCurrentSession();
//        s.save(u);
//        return u;
//    }
//
//    @Override
//    public User save(User user) {
//        Session s = this.factory.getObject().getCurrentSession();
//        s.save(user);
//        return user;
//    }

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
        Session s = this.factory.getObject().getCurrentSession();
        User user = s.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void enableUser(User u) {
        u.setEnabled(true);
    }
    
}
