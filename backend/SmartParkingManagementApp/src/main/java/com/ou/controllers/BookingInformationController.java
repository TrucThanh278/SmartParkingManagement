package com.ou.controllers;

import com.ou.configs.MomoConfigs;
import com.ou.pojo.BookingInformation;
import com.ou.services.BookingInformationService;
import com.ou.services.MomoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author trucn
 */
@Controller
public class BookingInformationController {

    @Autowired
    private BookingInformationService bookingInformationService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private MomoConfigs momoConfig;
    
    @Autowired
    private MomoService momoService;
    
    public String getBookingInfoList(Model model ,Integer id) {
        List<BookingInformation> bookingList = this.bookingInformationService.getBookingListWithPakingSpotId(id);
        model.addAttribute("bookingList", bookingList);
        return "detail";
    }
    
    
    @GetMapping("/buyticket")
    public String buyTicket(){
        return "ticket";
    }
    
    @PostMapping("/initiate-payment")
    public String aaaa(@RequestParam("orderId") String orderId, @RequestParam("amount") long amount){
        String r = this.momoService.initialPayment(orderId, amount, request);
        return "redirect: " + r;
    }
}
