/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.VehicleCategory;
import com.ou.repositories.VehicleCategoryRepository;
import com.ou.services.VehicleCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    @Override
    public List<VehicleCategory> getAllVehicleCategories() {
        return this.vehicleCategoryRepository.getCates();
    }

}
