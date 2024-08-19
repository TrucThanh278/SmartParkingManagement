/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.ParkingLot;
import com.ou.repositories.ParkingLotRepository;
import com.ou.services.ParkingLotService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService{
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    
    @Override
    public List<ParkingLot> getParkingLots() {
        return this.parkingLotRepository.getParkingLots();
    }

    @Override
    public List<ParkingLot> getParkingLotsByName(Map<String, String> params) {
        return this.parkingLotRepository.getParkingLotsByName(params);
    }

    @Override
    public ParkingLot getParkingLotDetail(Integer id) {
        return this.parkingLotRepository.getParkingLotDetail(id);
    }

    @Override
    public ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot) {
        return this.parkingLotRepository.updateParkingLotInfo(id, updatedParkingLot);
    }

    @Override
    public ParkingLot createParkingLot(ParkingLot p) {
        return this.parkingLotRepository.createParkingLot(p);
    }

    @Override
    public boolean deleteParkingLot(Integer id) {
        return this.parkingLotRepository.deleteParkingLot(id);
    }
    
}
