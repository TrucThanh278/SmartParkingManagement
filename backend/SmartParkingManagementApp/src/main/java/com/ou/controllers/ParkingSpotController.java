/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.pojo.BookingInformation;
import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.services.BookingInformationService;
import com.ou.services.ParkingSpotService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trucn
 */
@Controller
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;
    
    @Autowired
    private BookingInformationService bookingInformationService;
   
    @GetMapping("/parkingSpots")
    public String getParkingSpots(@RequestParam(required = false) String parkingLotID,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   Model model, HttpSession session) throws JsonProcessingException{
        ParkingLot parkingLot =(ParkingLot) session.getAttribute("parkingLot");
        Map<String, String> params = new HashMap<>();
        params.put("parkingLotID", parkingLotID);
        params.put("page", String.valueOf(page));
        int totalPages = this.parkingSpotService.getTotalPages(parkingLotID);
        
        List<ParkingSpot> parkingSpots = this.parkingSpotService.getParkingSpots(params);
        model.addAttribute("parkingSpots", parkingSpots);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "showParkingSpots";
    }
    
    
    @GetMapping("/parkingSpot/{id}/bookings")
    public String viewBookings(@PathVariable("id") int spotId, Model model) {
        List<BookingInformation> bookings = this.bookingInformationService.getBookingListWithPakingSpotId(spotId);
        model.addAttribute("bookings", bookings);
        return "showBookingInformation";
    }
    
}
