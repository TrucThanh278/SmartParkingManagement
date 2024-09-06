/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.ParkingSpot;
import com.ou.repositories.ParkingSpotsRepository;
import com.ou.services.ParkingSpotService;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    @Autowired
    private ParkingSpotsRepository parkingSpotsRepository;

    @Override
    public List<ParkingSpot> parkingSpots(int id) {
        return this.parkingSpotsRepository.getParkingSpotsWithParkingLot(id);
    }

    @Override

    public ParkingSpot findById(Integer id) {
        return parkingSpotsRepository.findById(id);
    }

    public void addParkingSpots(List<ParkingSpot> pss) {
        this.parkingSpotsRepository.addParkingSpots(pss);
    }

    @Override
    public List<ParkingSpot> getParkingSpots(Map<String, String> params) {
        return this.parkingSpotsRepository.getParkingSpots(params);
    }

    @Override
    public int getTotalPages(String parkingLotID) {
        return this.parkingSpotsRepository.getTotalPages(parkingLotID);
    }

}
