/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.repositories.Impl;

import com.ou.pojo.VehicleCategory;
import com.ou.repositories.VehicleCategoryRepository;
import java.util.List;
import javax.persistence.Query;
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
public class VehicleCategoryRepositoryImpl implements VehicleCategoryRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<VehicleCategory> getCates() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From VehicleCategory");
        return q.getResultList();
    }
}
