package com.ou.controllers;

import com.ou.dto.request.BookingInfoRequestDTO;
import com.ou.dto.request.BookingInfoUpdateRequestDTO;
import com.ou.dto.response.BookingInformationResponseDTO;
import com.ou.pojo.BookingInformation;
import com.ou.services.BookingInformationService;
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
}
