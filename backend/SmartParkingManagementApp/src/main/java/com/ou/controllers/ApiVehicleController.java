package com.ou.controllers;

import com.ou.dto.request.VehicleRequestDTO;
import com.ou.pojo.Vehicle;
import com.ou.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class ApiVehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleRequestDTO dtoVehicleRequest) {
        Vehicle vehicle = vehicleService.addVehicle(dtoVehicleRequest);
        return ResponseEntity.ok(vehicle);
    }
}
