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
    List<ParkingSpot> getParkingSpots(Map<String, String> params);

    List<ParkingSpot> getParkingSpotsWithParkingLot(int ID);

    void addParkingSpot(int num);

    ParkingSpot findById(Integer id);

    ParkingSpot save(ParkingSpot parkingSpot);

    void addParkingSpots(List<ParkingSpot> p);

    int getTotalPages(String parkingLotID);
}
