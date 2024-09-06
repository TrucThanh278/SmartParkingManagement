package com.ou.controllers;

import com.ou.dto.request.VehicleRequestDTO;
import com.ou.pojo.Vehicle;
import com.ou.services.VehicleService;
import java.util.List;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable("userId") int userId) {
        try {
            List<Vehicle> vehicles = vehicleService.getVehicleWithUserID(userId);
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            // Logging and handling exception
            return ResponseEntity.status(500).body(null); // Customize error response as needed
        }
    }
}
