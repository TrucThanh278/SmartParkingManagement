/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.ParkingSpot;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface ParkingSpotService {

    public List<ParkingSpot> parkingSpots(int id);

    ParkingSpot findById(Integer id);
}
