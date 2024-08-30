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
    
    @Autowired
    private BookingInformationService bookingInformationService;
   
     
    
    @GetMapping("/parkingSpots/{id}")
    public String getParkingSpots(Model model, @PathVariable(name = "id") int id, HttpSession session) throws JsonProcessingException{
        List<ParkingSpot> parkingSpots = this.parkingSpotService.parkingSpots(id);
        ParkingLot parkingLot =(ParkingLot) session.getAttribute("parkingLot");
        
//        System.out.println("parkingLot >>>>> " + parkingLot.getParkingSpotList());
//        for(ParkingSpot p : parkingLot.getParkingSpotList()){
//            System.out.println(">>>>>>> ParkingSpot Item: " + p.getSpotNumber());
//        }
        
//        List<BookingInformation> bookingList = this.bookingInformationService.getBookingListWithPakingSpotId((Integer) id);
//        for(BookingInformation b : bookingList){
//                    System.out.println(">>>>>>>> Booking List: " + b.getVehicleId());
//        }

        List<BookingInformation> bookingList = this.bookingInformationService.getBookingListOfParkingLot((Integer) parkingLot.getId());
       
        
        
        model.addAttribute("parkingSpots", parkingSpots);
        model.addAttribute("parkingLot", parkingLot);
        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String bookingListJSON= objectMapper.writeValueAsString(bookingList);
        String parkingSpotListJSON = objectMapper.writeValueAsString(parkingLot.getParkingSpotList());
        System.out.println(">>>>>>>>>>>>>JSON: " + bookingListJSON);
        model.addAttribute("bookingListJSON", bookingListJSON);
        model.addAttribute("parkingSpotListJSON", parkingSpotListJSON);
        return "showParkingSpots";
    }
}
