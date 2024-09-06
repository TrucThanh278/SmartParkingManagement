package com.ou.controllers;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.pojo.ParkingLot;
import com.ou.services.ParkingLotService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parkinglots")
public class ApiParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public Map<String, Object> getParkingLots(
            @RequestParam Map<String, String> params) {
        return parkingLotService.getParkingLots(params);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLotResponseDTO> getParkingLotById(@PathVariable("id") Integer id) {
        ParkingLotResponseDTO dtoParkingLotResponse = parkingLotService.getDTOParkingLotDetail(id);
        return ResponseEntity.ok(dtoParkingLotResponse);
    }

    @GetMapping("/search")
    public Map<String, Object> getParkingLots(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "sortByPriceAsc", defaultValue = "false") boolean sortByPriceAsc) {
        return parkingLotService.findParkingLots(name, address, sortByPriceAsc);
    }
}
