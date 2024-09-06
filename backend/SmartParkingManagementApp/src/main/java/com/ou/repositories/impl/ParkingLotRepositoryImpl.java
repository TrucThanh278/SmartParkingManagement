/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.repositories.ParkingLotRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    
    private static final int PAGE_SIZE = 6;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Map<String, Object> getParkingLots(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<ParkingLot> criteriaQuery = builder.createQuery(ParkingLot.class);
        Root<ParkingLot> root = criteriaQuery.from(ParkingLot.class);
        criteriaQuery.select(root);
        TypedQuery<ParkingLot> hqlQuery = session.createQuery(criteriaQuery);

        if (params != null) {
            String pageParam = params.get("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                int page = Integer.parseInt(pageParam);
                int start = (page - 1) * PAGE_SIZE;

                hqlQuery.setFirstResult(start);
                hqlQuery.setMaxResults(PAGE_SIZE);
            }
        }

        List<ParkingLot> parkingLots = hqlQuery.getResultList();

        if (params != null){
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<ParkingLot> countRoot = countQuery.from(ParkingLot.class);
            countQuery.select(builder.count(countRoot));
            TypedQuery<Long> countQ = session.createQuery(countQuery);
            long totalItems = countQ.getSingleResult();

            long totalPages = (totalItems + PAGE_SIZE - 1) / PAGE_SIZE;

            Map<String, Object> result = new HashMap<>();
            result.put("data", parkingLots);
            result.put("totalPages", totalPages);
            result.put("totalItems", totalItems);
            return result;
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("data", parkingLots);

        return result;
    }

    @Override
    public List<ParkingLot> getParkingLotsByName(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
        Root root = q.from(ParkingLot.class);
        q.select(root);

        if (params != null) {
            String kw = params.get("q");
            if (kw != null & !kw.isEmpty()) {
                Predicate namePredicate = b.like(root.get("name"), String.format("%%%s%%", kw));
                q.where(namePredicate);
            }
        }

        Query query = s.createQuery(q);
        return query.getResultList();

    }

    @Override
    public ParkingLot getParkingLotDetail(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
        Root<ParkingLot> root = q.from(ParkingLot.class);
        q.select(root);

        Predicate idPredicate = b.equal(root.get("id"), id);
        q.where(idPredicate);

        Query query = s.createQuery(q);
        return (ParkingLot) query.getSingleResult();

    }

    @Override
    public ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot) {
        Session s = this.factory.getObject().getCurrentSession();
        ParkingLot currentParkingLot = s.get(ParkingLot.class, id);
        if (currentParkingLot != null) {
            currentParkingLot.setName(updatedParkingLot.getName());
            currentParkingLot.setAddress(updatedParkingLot.getAddress());
            currentParkingLot.setDescription(updatedParkingLot.getDescription());
            currentParkingLot.setPricePerHour(updatedParkingLot.getPricePerHour());
            currentParkingLot.setTotalSpots(updatedParkingLot.getTotalSpots());
            currentParkingLot.setStartTime(updatedParkingLot.getStartTime());
            currentParkingLot.setEndTime(updatedParkingLot.getEndTime());
        }
        s.update(currentParkingLot);
        return currentParkingLot;

    }

    @Override
    public ParkingLot createParkingLot(ParkingLot newParkingLot) {
        Session s = this.factory.getObject().getCurrentSession();
        if (newParkingLot.getId() == null) {
            s.save(newParkingLot);
        } else {
            s.update(newParkingLot);
        }
        return newParkingLot;

    }

    @Override
    public void deleteParkingLot(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        ParkingLot p = s.get(ParkingLot.class, id);
        s.delete(p);
    }

    @Override
    public Map<String, Object> findParkingLots(String name, String address, boolean sortByPriceAsc) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Criteria API for Sorting and Pagination
        CriteriaQuery<ParkingLot> cq = cb.createQuery(ParkingLot.class);
        Root<ParkingLot> root = cq.from(ParkingLot.class);

        // JPQL for Filtering
        String jpql = "SELECT p FROM ParkingLot p WHERE 1=1";
        if (name != null && !name.isEmpty()) {
            jpql += " AND LOWER(p.name) LIKE :name";
        }
        if (address != null && !address.isEmpty()) {
            jpql += " AND LOWER(p.address) LIKE :address";
        }

        if (sortByPriceAsc) {
            jpql += " ORDER BY p.pricePerHour ASC";
        } else {
            jpql += " ORDER BY p.pricePerHour DESC";
        }

        // Create JPQL Query
        TypedQuery<ParkingLot> query = session.createQuery(jpql, ParkingLot.class);
        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }
        if (address != null && !address.isEmpty()) {
            query.setParameter("address", "%" + address.toLowerCase() + "%");
        }


        query.setMaxResults(PAGE_SIZE);
        query.setFirstResult(0);

        List<ParkingLot> parkingLots = query.getResultList();

        // Count Query
        String countJpql = "SELECT COUNT(p) FROM ParkingLot p WHERE 1=1";
        if (name != null && !name.isEmpty()) {
            countJpql += " AND LOWER(p.name) LIKE :name";
        }
        if (address != null && !address.isEmpty()) {
            countJpql += " AND LOWER(p.address) LIKE :address";
        }

        TypedQuery<Long> countQuery = session.createQuery(countJpql, Long.class);
        if (name != null && !name.isEmpty()) {
            countQuery.setParameter("name", "%" + name.toLowerCase() + "%");
        }
        if (address != null && !address.isEmpty()) {
            countQuery.setParameter("address", "%" + address.toLowerCase() + "%");
        }

        Long totalItems = countQuery.getSingleResult();

        Map<String, Object> result = new HashMap<>();
        result.put("totalItems", totalItems);
        result.put("data", parkingLots);
        result.put("totalPages", (int) Math.ceil((double) totalItems / PAGE_SIZE));

        return result;
    }

    @Override
    public ParkingLot findById(Integer id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(ParkingLot.class, id);
    }

}
