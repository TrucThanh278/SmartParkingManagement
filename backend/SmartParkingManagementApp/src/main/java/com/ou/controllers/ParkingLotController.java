/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.pojo.ParkingLot;
import com.ou.services.ParkingLotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 *
 * @author trucn
 */
@SessionAttributes("parkingLot")
@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    
    @GetMapping("/")
    public String getIndexPage(){
        return "home";
    }
    
    @GetMapping("/parkingLot")
    public String getParkingLot(Model model){
        List<ParkingLot> parkingLots = this.parkingLotService.getParkingLots();
        model.addAttribute("parkingLots", parkingLots);
        return "showParkingLot";
    }
    
    @GetMapping("/parkingLot/{id}")
    public String getParkingLotDetail(Model model, @PathVariable(name = "id") Integer id){
        ParkingLot parkingLot = this.parkingLotService.getParkingLotDetail(id);
        model.addAttribute("parkingLot", parkingLot);
        return "detailParkingLot";
    }
}
