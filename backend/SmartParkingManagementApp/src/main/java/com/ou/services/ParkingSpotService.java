/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.ParkingSpot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public interface ParkingSpotService {
    List<ParkingSpot> parkingSpots(int id);
    ParkingSpot findById(Integer id);
    List<ParkingSpot> getParkingSpots(Map<String, String> params);
    void addParkingSpots(List<ParkingSpot> p);
    int getTotalPages(String parkingLotID);
}
