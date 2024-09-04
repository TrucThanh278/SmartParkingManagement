/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.dto.request.VehicleRequestDTO;
import com.ou.mappers.VehicleMapper;
import com.ou.pojo.Vehicle;
import com.ou.repositories.VehicleRepository;
import com.ou.services.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class VehicleServiceIml implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public List<Vehicle> getVehicleWithUserID(int id) {
        return this.vehicleRepository.getVehicleWithUserID(id);
    }

    @Override
    public Vehicle addVehicle(VehicleRequestDTO dtoVehicleRequest) {
        Vehicle vehicle = vehicleMapper.addVehicle(dtoVehicleRequest);

        return vehicleRepository.save(vehicle);
    }

}
