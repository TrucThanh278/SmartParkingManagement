/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.ParkingSpot;
import com.ou.repositories.ParkingSpotsRepository;
import com.ou.services.ParkingSpotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{
    @Autowired
    private ParkingSpotsRepository parkingSpotsRepository;
    
    @Override
    public List<ParkingSpot> parkingSpots(int id){
        return this.parkingSpotsRepository.getParkingSpotsWithParkingLot(id);
    }
   
}
