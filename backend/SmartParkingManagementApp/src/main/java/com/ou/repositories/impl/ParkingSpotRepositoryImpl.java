/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.ParkingSpot;
import com.ou.repositories.ParkingSpotsRepository;

import java.util.List;
import java.util.Map;
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
public class ParkingSpotRepositoryImpl implements ParkingSpotsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ParkingSpot> getParkingSpots(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingSpot> q = b.createQuery(ParkingSpot.class);
        Root root = q.from(ParkingSpot.class);
        q.select(root);

        Query query = s.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<ParkingSpot> getParkingSpotsWithParkingLot(int ID) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingSpot> q = b.createQuery(ParkingSpot.class);
        Root root = q.from(ParkingSpot.class);
        Predicate idPredicate = b.equal(root.get("parkingLotId").get("id"), ID);
        q.select(root).where(idPredicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addParkingSpot(int num) {
        Session s = this.factory.getObject().getCurrentSession();
        for (int i = 0; i < num; i++) {

        }
    }

    @Override
    public ParkingSpot findById(Integer id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(ParkingSpot.class, id);
    }

    @Override
    public ParkingSpot save(ParkingSpot ps) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(ps);
        return ps;
    }

}
