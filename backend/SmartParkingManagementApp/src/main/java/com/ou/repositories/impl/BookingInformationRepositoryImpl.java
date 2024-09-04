/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.BookingInformation;
import com.ou.repositories.BookingInformationRepository;
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

}
