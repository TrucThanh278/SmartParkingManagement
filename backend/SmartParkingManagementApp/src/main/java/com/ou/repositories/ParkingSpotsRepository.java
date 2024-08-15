/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.ParkingSpot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public interface ParkingSpotsRepository {
    public List<ParkingSpot> getParkingSpots(Map<String, String> params);
    public List<ParkingSpot> getParkingSpotsWithParkingLot(int ID);
}
