/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.ParkingLot;
import com.ou.repositories.ParkingLotRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
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

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ParkingLot> getParkingLots() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
        Root root = q.from(ParkingLot.class);

        q.select(root);
        Query query = s.createQuery(q);
        return query.getResultList();

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
    public List<ParkingLot> findParkingLots(String name, String address, boolean sortByPriceAsc) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> cq = cb.createQuery(ParkingLot.class);
        Root<ParkingLot> root = cq.from(ParkingLot.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            Expression<String> nameExpression = root.get("name");
            Predicate namePredicate = cb.like(cb.lower(nameExpression), "%" + name.toLowerCase() + "%");
            predicates.add(namePredicate);
        }
        if (address != null && !address.isEmpty()) {
            Expression<String> addressExpression = root.get("address");
            Predicate addressPredicate = cb.like(cb.lower(addressExpression), "%" + address.toLowerCase() + "%");
            predicates.add(addressPredicate);
        }

        cq.where(predicates.toArray(new Predicate[0]));
        if (sortByPriceAsc) {
            cq.orderBy(cb.asc(root.get("pricePerHour")));
        } else {
            cq.orderBy(cb.desc(root.get("pricePerHour")));
        }

        return s.createQuery(cq).getResultList();
    }

}
