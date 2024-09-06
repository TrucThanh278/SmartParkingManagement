/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.ParkingLot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public interface ParkingLotRepository {


    Map<String, Object> getParkingLots(Map<String, String> params);

    List<ParkingLot> getParkingLotsByName(Map<String, String> params);

    ParkingLot getParkingLotDetail(Integer id);

    ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot);

    ParkingLot createParkingLot(ParkingLot p);

    void deleteParkingLot(Integer id);
    
    Map<String, Object> findParkingLots(String name, String address, boolean sortByPriceAsc);
    
    ParkingLot findById(Integer id);
}
