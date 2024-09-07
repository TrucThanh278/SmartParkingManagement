package com.ou.controllers;

import com.ou.services.MomoService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class MomoController {

    @Autowired
    private MomoService momoService;
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/initiate")
    public ResponseEntity<Void> initiatePayment(@RequestParam String orderId, @RequestParam long amount) {
        String payUrl = this.momoService.initialPayment(orderId, amount, request);
        return ResponseEntity.status(HttpStatus.FOUND)
                                .location(URI.create(payUrl))
                                .build();
    }

    @PostMapping("/payment-notification")
    public String handlePaymentNotification(@RequestBody Map<String, String> paymentResult) {
        // Xác thực kết quả thanh toán
        boolean isVerified = momoService.verifyPaymentResult(paymentResult);
        if (isVerified) {
            // Xử lý khi thanh toán thành công
            // Cập nhật trạng thái đơn hàng trong cơ sở dữ liệu
            return "Payment success!";
        } else {
            // Xử lý khi thanh toán không hợp lệ
            return "Payment verification failed!";
        }
    }

    @PostMapping("/return")
    public String handleReturn(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }

        boolean isVerified = momoService.verifyPaymentResult(params);
        if (isVerified) {
            // Xử lý khi thanh toán thành công
            return "Thanh toán thành công!";
        } else {
            // Xử lý khi thanh toán không thành công
            return "Xác thực thanh toán thất bại!";
        }
    }
}
