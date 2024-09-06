/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.Role;
import com.ou.repositories.RoleRepository;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trucn
 */
@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getRoles() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Role");
        return q.getResultList();
    }
    
    @Override
    public Role getRoleByName(String name){
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Role> q = b.createQuery(Role.class);
        Root root = q.from(Role.class);
        q.select(root);
        
        Predicate p = b.equal(root.get("name"), name);
        q.where(p);
        
        Query query = s.createQuery(q);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role findById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Role> q = b.createQuery(Role.class);
        Root root = q.from(Role.class);
        Predicate idPredicate = b.equal(root.get("id"), id);
        q.select(root).where(idPredicate);
        
        Query query = s.createQuery(q);
        return (Role) query.getSingleResult();
    }

}
