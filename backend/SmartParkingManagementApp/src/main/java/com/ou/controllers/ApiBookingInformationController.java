package com.ou.controllers;

import com.ou.dto.request.BookingInfoRequestDTO;
import com.ou.dto.request.BookingInfoUpdateRequestDTO;
import com.ou.dto.response.BookingInformationResponseDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.pojo.BookingInformation;
import com.ou.pojo.Vehicle;
import com.ou.services.BookingInformationService;
import com.ou.services.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookinginfo")
@CrossOrigin
public class ApiBookingInformationController {

    @Autowired
    private BookingInformationService bookingInformationService;
    
    @Autowired
    private UserService userService;

    @PostMapping(path = "/booking", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<?> addBookingInfo(@RequestBody BookingInfoRequestDTO dtoBookingInfoRequest) {
        BookingInformation bookingInformation = bookingInformationService.addBookingInfo(dtoBookingInfoRequest);
        return ResponseEntity.ok(bookingInformation);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BookingInformationResponseDTO> updateBookingInfo(@PathVariable Integer id, @RequestBody BookingInfoUpdateRequestDTO dtoBookingInfoUpdateRequest) {
        BookingInformationResponseDTO response = bookingInformationService.updateBookingInfo(id, dtoBookingInfoUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookingInfo(@PathVariable Integer id) {
        try {
            bookingInformationService.deleteBookingInfo(id);
            return ResponseEntity.ok("Booking information deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/current-date/{parkingSpotId}")
    public ResponseEntity<List<BookingInformation>> getBookingListByCurrentDateAndParkingSpotId(
            @PathVariable("parkingSpotId") Integer parkingSpotId) {
        LocalDate currentDate = LocalDate.now();
        List<BookingInformation> bookings = bookingInformationService.getBookingListByCurrentDateAndParkingSpotId(currentDate, parkingSpotId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping
    public ResponseEntity<List<BookingInformation>> getBookingInforWithUserId(){
        Integer currentUserId = this.userService.getMyInfo().getId();
        List<BookingInformation> bookingList = this.bookingInformationService.getBookingListWithUserId(currentUserId);
        return ResponseEntity.ok(bookingList);
    }
    
}


//UserResponseDTO u = this.userService.getMyInfo();
