/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;


import com.ou.pojo.BookingInformation;
import com.ou.pojo.Vehicle;
import com.ou.repositories.BookingInformationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
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
public class BookingInformationRepositoryImpl implements BookingInformationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<BookingInformation> getBookingListWithParkingSpotId(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<BookingInformation> q = b.createQuery(BookingInformation.class);
        Root root = q.from(BookingInformation.class);

        Predicate idPredicate = b.equal(root.get("parkingSpotId").get("id"), id);
        q.where(idPredicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<BookingInformation> getBookingListOfParkingLot(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<BookingInformation> q = b.createQuery(BookingInformation.class);
        Root root = q.from(BookingInformation.class);
        Predicate idPredicate = b.equal(root.get("parkingSpotId").get("parkingLotId").get("id"), id);
        q.where(idPredicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public BookingInformation saveBookingInfo(BookingInformation bookingInformation) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(bookingInformation);
        return bookingInformation;
    }

    @Override
    public BookingInformation findById(Integer id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(BookingInformation.class, id);
    }

    @Override
    public void deleteBookingInfo(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        BookingInformation p = s.get(BookingInformation.class, id);
        s.delete(p);
    }

    @Override
    public List<BookingInformation> getBookingListByCurrentDateAndParkingSpotId(LocalDate currentDate, Integer parkingSpotId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BookingInformation> query = builder.createQuery(BookingInformation.class);
        Root<BookingInformation> root = query.from(BookingInformation.class);

        LocalDateTime startOfDay = currentDate.atStartOfDay();
        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);

        Predicate timePredicate = builder.between(root.get("startTime"), startOfDay, endOfDay);
        
        Predicate spotIdPredicate = builder.equal(root.get("parkingSpotId").get("id"), parkingSpotId);

        query.where(builder.and(timePredicate, spotIdPredicate));

        org.hibernate.query.Query<BookingInformation> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<BookingInformation> getBookingListWithUserId(int userId) {
        Session session = this.factory.getObject().getCurrentSession();
        
        String hql = "FROM BookingInformation b WHERE b.vehicleId.userId.id = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        
        return query.getResultList();
    }

}
