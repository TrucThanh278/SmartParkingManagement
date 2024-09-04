/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.dto.request.VehicleRequestDTO;
import com.ou.pojo.Vehicle;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface VehicleService {
    public List<Vehicle> getVehicleWithUserID(int id);
    Vehicle addVehicle(VehicleRequestDTO dtoVehicleRequest);
}
