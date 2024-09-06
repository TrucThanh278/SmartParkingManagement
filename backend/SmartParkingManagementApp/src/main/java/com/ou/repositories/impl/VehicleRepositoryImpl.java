/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.Vehicle;
import com.ou.repositories.VehicleRepository;
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
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Vehicle> getVehicleWithUserID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vehicle> q = b.createQuery(Vehicle.class);
        Root root = q.from(Vehicle.class);
        Predicate idPredicate = b.equal(root.get("userId").get("id"), id);
        q.select(root).where(idPredicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Vehicle findById(Integer id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Vehicle.class, id);
    }

    @Override
    public Vehicle save(Vehicle vhcl) {
        Session session = this.factory.getObject().getCurrentSession();
        session.saveOrUpdate(vhcl);
        return vhcl;
    }

}
