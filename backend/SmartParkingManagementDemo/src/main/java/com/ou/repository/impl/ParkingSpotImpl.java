/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repository.impl;

import com.ou.pojo.ParkingSpot;
import com.ou.smartparkingmanagementdemo.SmartParkingMangementDemoUtils;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author trucn
 */
public class ParkingSpotImpl {
    public List<ParkingSpot> getParkingSpots(Map<String, String> params) {
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<ParkingSpot> q = b.createQuery(ParkingSpot.class);
            Root root = q.from(ParkingSpot.class);
            
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    public List<ParkingSpot> getParkingSpotsWithParkingLot(int ID){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<ParkingSpot> q = b.createQuery(ParkingSpot.class);
            Root root = q.from(ParkingSpot.class);
            Predicate idPredicate = b.equal(root.get("parkingLotId").get("id"), ID);
            q.select(root).where(idPredicate);
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
}
