/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repository.impl;

import com.ou.pojo.ParkingLot;
import com.ou.smartparkingmanagementdemo.SmartParkingMangementDemoUtils;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author trucn
 */
public class ParkingLotImpl {
    public List<ParkingLot> getParkingLots(){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
            Root root = q.from(ParkingLot.class);
            
            q.select(root);
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    public List<ParkingLot> getParkingLotsByName(Map<String, String> params){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
            Root root = q.from(ParkingLot.class);
            q.select(root);
            
            String kw = params.get("q");
            if(kw != null & !kw.isEmpty()){
                Predicate namePredicate = b.like(root.get("name"), String.format("%%%s%%", kw));
                q.where(namePredicate);
            }
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    
    public ParkingLot getParkingLotDetail(Integer id){
        try (Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<ParkingLot> q =b.createQuery(ParkingLot.class);
            Root<ParkingLot> root = q.from(ParkingLot.class);
            q.select(root);
            
            Predicate idPredicate = b.equal(root.get("id"), id);
            q.where(idPredicate);
            
            Query query = s.createQuery(q);
            return (ParkingLot) query.getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    public ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            Transaction transaction = s.beginTransaction();
            ParkingLot currentParkingLot = s.get(ParkingLot.class, id);
            if(currentParkingLot != null){
                currentParkingLot.setName(updatedParkingLot.getName());                
                currentParkingLot.setAddress(updatedParkingLot.getAddress());
                currentParkingLot.setDescription(updatedParkingLot.getDescription());
                currentParkingLot.setPricePerHour(updatedParkingLot.getPricePerHour());
                currentParkingLot.setTotalSpots(updatedParkingLot.getTotalSpots());
                currentParkingLot.setStartTime(updatedParkingLot.getStartTime());
                currentParkingLot.setEndTime(updatedParkingLot.getEndTime());
            }
            s.update(currentParkingLot);
            transaction.commit();
            return currentParkingLot;
        }
    }
    
    public ParkingLot createParkingLot(String name, String address, Integer total_spots, Float pricePerHour, String description, LocalDateTime startTime, LocalDateTime endTime){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            Transaction transaction = s.beginTransaction();
            ParkingLot newParkingLot = new ParkingLot();
            newParkingLot.setName(name);
            newParkingLot.setAddress(address);
            newParkingLot.setDescription(description);
            newParkingLot.setTotalSpots(total_spots);
            newParkingLot.setPricePerHour(pricePerHour);
            newParkingLot.setStartTime(startTime);
            newParkingLot.setEndTime(endTime);
            s.save(newParkingLot);
            transaction.commit();
            return newParkingLot;
        }
    }
    
    public boolean deleteParkingLot(int id){
        try(Session s = SmartParkingMangementDemoUtils.getFactory().openSession()){
            Transaction transaction = s.beginTransaction();
            ParkingLot parkingLot = s.get(ParkingLot.class, id);
            if(parkingLot != null){
                s.delete(parkingLot);
                transaction.commit();
                return true;
            }
            transaction.commit();
            return false;
        }
    }
    
    
}
