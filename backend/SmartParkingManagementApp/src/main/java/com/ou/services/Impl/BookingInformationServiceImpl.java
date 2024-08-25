/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.BookingInformation;
import com.ou.repositories.BookingInformationRepository;
import com.ou.services.BookingInformationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class BookingInformationServiceImpl implements BookingInformationService{
    @Autowired
    private BookingInformationRepository bookingInformationRepository;
    
    @Override
    public List<BookingInformation> getBookingListWithPakingSpotId(Integer id){
        return this.bookingInformationRepository.getBookingListWithParkingSpotId(id);
    }

    @Override
    public List<BookingInformation> getBookingListOfParkingLot(Integer id) {
        return this.bookingInformationRepository.getBookingListOfParkingLot(id);
    }
}
