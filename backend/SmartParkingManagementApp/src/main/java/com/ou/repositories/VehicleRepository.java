/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.Vehicle;
import com.ou.pojo.VehicleCategory;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface VehicleRepository {
    public List<Vehicle> getVehicleWithUserID(int id);
}
