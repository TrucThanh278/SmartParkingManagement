/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.services.ParkingSpotService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author trucn
 */
@Controller
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;
    
    @GetMapping("/parkingSpots/{id}")
    public String getParkingSpots(Model model, @PathVariable(name = "id") int id, HttpSession session){
        List<ParkingSpot> parkingSpots = this.parkingSpotService.parkingSpots(id);
        ParkingLot parkingLot =(ParkingLot) session.getAttribute("parkingLot");
        model.addAttribute("parkingSpots", parkingSpots);
        model.addAttribute("parkingLot", parkingLot);
        return "showParkingSpots";
    }
}
