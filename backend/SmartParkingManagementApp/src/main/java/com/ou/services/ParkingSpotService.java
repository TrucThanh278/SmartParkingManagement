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
    List<ParkingSpot> getParkingSpots(Map<String, String> params);
    public List<ParkingSpot> parkingSpots(int id);
    void addParkingSpots(List<ParkingSpot> p);
    int getTotalPages(String parkingLotID);
}
