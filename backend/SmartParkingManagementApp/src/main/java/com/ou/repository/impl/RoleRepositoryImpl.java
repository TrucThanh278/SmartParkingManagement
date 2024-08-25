package com.ou.repository.impl;

import com.ou.pojo.Role;
import com.ou.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findById(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role == null) {
            throw new RuntimeException("Role not found with ID: " + id);
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Role not found with name: " + name, e);
        }
    }
}
