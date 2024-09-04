package com.ou.controllers;

import com.ou.dto.response.DTOParkingLotResponse;
import com.ou.pojo.ParkingLot;
import com.ou.services.ParkingLotService;
import java.util.List;
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
    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotService.getParkingLots();
        return ResponseEntity.ok(parkingLots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOParkingLotResponse> getParkingLotById(@PathVariable("id") Integer id) {
        DTOParkingLotResponse dtoParkingLotResponse = parkingLotService.getDTOParkingLotDetail(id);
        return ResponseEntity.ok(dtoParkingLotResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DTOParkingLotResponse>> searchParkingLots(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false, defaultValue = "true") boolean sortByPriceAsc) {

        List<DTOParkingLotResponse> parkingLots = parkingLotService.searchParkingLots(name, address, sortByPriceAsc);
        return ResponseEntity.ok(parkingLots);
    }
}
