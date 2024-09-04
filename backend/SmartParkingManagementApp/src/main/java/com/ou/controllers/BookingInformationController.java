/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.pojo.BookingInformation;
import com.ou.services.BookingInformationService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author trucn
 */
@Controller
public class BookingInformationController {

    @Autowired
    private BookingInformationService bookingInformationService;

    public String getBookingInfoList(Model model ,Integer id) {
        List<BookingInformation> bookingList = this.bookingInformationService.getBookingListWithPakingSpotId(id);
        model.addAttribute("bookingList", bookingList);
        return "detail";
    }
}
