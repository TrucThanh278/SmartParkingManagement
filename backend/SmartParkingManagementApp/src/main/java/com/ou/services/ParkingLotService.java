/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.ParkingLot;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public interface ParkingLotService {
    public List<ParkingLot> getParkingLots();
    public List<ParkingLot> getParkingLotsByName(Map<String, String> params);
    public ParkingLot getParkingLotDetail(Integer id);
    public ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot);
    public ParkingLot createParkingLot(ParkingLot p);
    public boolean deleteParkingLot(Integer id);
}
