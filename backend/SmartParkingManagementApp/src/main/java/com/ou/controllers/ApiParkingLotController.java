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
    public ResponseEntity<List<ParkingLot>> getAllParkingLots(@RequestParam(required = false, defaultValue = "1") int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));

        List<ParkingLot> parkingLots = parkingLotService.getParkingLots(params);
        return ResponseEntity.ok(parkingLots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLotResponseDTO> getParkingLotById(@PathVariable("id") Integer id) {
        ParkingLotResponseDTO dtoParkingLotResponse = parkingLotService.getDTOParkingLotDetail(id);
        return ResponseEntity.ok(dtoParkingLotResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParkingLotResponseDTO>> searchParkingLots(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false, defaultValue = "true") boolean sortByPriceAsc) {

        List<ParkingLotResponseDTO> parkingLots = parkingLotService.searchParkingLots(name, address, sortByPriceAsc);
        return ResponseEntity.ok(parkingLots);
    }
}
