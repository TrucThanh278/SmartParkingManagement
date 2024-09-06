/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.services.ParkingLotService;
import com.ou.services.ParkingSpotService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/")
    public String getParkingLots(Model model,
            @RequestParam Map<String, String> params) {
        Map<String, Object> parkingLots = new HashMap<>();
        parkingLots = this.parkingLotService.getParkingLots(params);
        model.addAttribute("parkingLots", parkingLots.get("data"));
        return "showParkingLot";
    }

    @GetMapping("/parkingLot/{id}")
    public String getParkingLotDetail(Model model, @PathVariable(name = "id") Integer id) {
        ParkingLot parkingLot = this.parkingLotService.getParkingLotDetail(id);
        model.addAttribute("parkingLot", parkingLot);
        return "detailParkingLot";
    }

    @GetMapping("/parkingLot/create")
    public String getCreateParkingLotPage(Model model) {
        model.addAttribute("parkingLot", new ParkingLot());
        return "createParkingLot";
    }

    @PostMapping("/parkingLot/create")
    public String addParkingLot(Model model,
            @ModelAttribute("parkingLot") @Valid ParkingLot parkingLot,
            BindingResult rs,
            @RequestParam("totalSpots") int totalSpots) {
        if (rs.hasErrors()) {
            List<FieldError> errors = rs.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(">>>>>>> Loi: " + error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "createParkingLot";
        } else {
            String namePrefix = parkingLot.getName().substring(0, Math.min(2, parkingLot.getName().length())).toUpperCase();
            List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
            for (int i = 1; i <= totalSpots; i++) {
                ParkingSpot ps = new ParkingSpot();
                ps.setParkingLotId(parkingLot);
                ps.setSpotNumber(namePrefix + i);
                ps.setStatus(false);
                parkingSpots.add(ps);
            }
            this.parkingLotService.createParkingLot(parkingLot);
            this.parkingSpotService.addParkingSpots(parkingSpots);
            return "redirect:/";
        }
    }

    @GetMapping("/parkingLot/update/{id}")
    public String getUpdateParkingLotPage(Model model, @PathVariable("id") Integer id) {
        ParkingLot parkingLot = this.parkingLotService.getParkingLotDetail(id);
        model.addAttribute("parkingLot", parkingLot);
        return "createParkingLot";
    }

    @PostMapping("/parkingLot/update")
    public String updateParkingLot(Model model, @ModelAttribute("parkingLot") @Valid ParkingLot parkingLot,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "createParkingLot";
        } else {
            this.parkingLotService.createParkingLot(parkingLot);
            return "redirect:/";
        }
    }

    @GetMapping("/parkingLot/delete/{id}")
    public String deleteParkingLot(@PathVariable("id") Integer id) {
        try {
            this.parkingLotService.deleteParkingLot(id);
        } catch (Exception e) {
            System.err.println("Delete ParkingLot Error: " + e.getMessage());
        }
        return "redirect:/";
    }

}
