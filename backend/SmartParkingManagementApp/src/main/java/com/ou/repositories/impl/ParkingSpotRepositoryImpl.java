/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.ParkingSpot;
import com.ou.repositories.ParkingSpotsRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    private static final int PAGE_SIZE = 30;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ParkingSpot> getParkingSpots(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingSpot> q = b.createQuery(ParkingSpot.class);
        Root root = q.from(ParkingSpot.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String parkingLotID = params.get("parkingLotID");
            if (parkingLotID != null && !parkingLotID.isEmpty()) {
                Predicate p1 = b.equal(root.get("parkingLotId").get("id"), Integer.parseInt(parkingLotID));
                predicates.add(p1);
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;

                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
        }

        List<ParkingSpot> spots = query.getResultList();
        LocalDateTime now = LocalDateTime.now();

        for (ParkingSpot spot : spots) {
            boolean isOccupied = spot.getBookingInformationList().stream()
                    .anyMatch(booking -> booking.isSpotOccupied(now));
            spot.setStatus(isOccupied);
        }

        return spots;

    }

    public int getTotalPages(String parkingLotID) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(ParkingSpot.class);

        q.select(b.count(root));
        if (parkingLotID != null && !parkingLotID.isEmpty()) {
            q.where(b.equal(root.get("parkingLotId").get("id"), Integer.parseInt(parkingLotID)));
        }

        long totalSpots = s.createQuery(q).getSingleResult();
        int totalPages = (int) Math.ceil((double) totalSpots / PAGE_SIZE);
        return totalPages;
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

    @Override
    public void addParkingSpots(List<ParkingSpot> p) {
        Session s = this.factory.getObject().getCurrentSession();
        for (ParkingSpot ps : p) {
            s.save(ps);
        }
    }

}
