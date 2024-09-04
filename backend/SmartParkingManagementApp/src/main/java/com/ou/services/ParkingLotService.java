/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.pojo.ParkingLot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public interface ParkingLotService {

    List<ParkingLot> getParkingLots();

    List<ParkingLot> getParkingLotsByName(Map<String, String> params);

    ParkingLot getParkingLotDetail(Integer id);

    ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot);

    ParkingLot createParkingLot(ParkingLot p);

    void deleteParkingLot(Integer id);
    
    ParkingLotResponseDTO getDTOParkingLotDetail(Integer id);
    
    List<ParkingLotResponseDTO> searchParkingLots(String name, String address, boolean sortByPriceAsc);
}
