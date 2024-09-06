package com.ou.services;

import com.ou.configs.MomoConfigs;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MomoService {

    @Autowired
    private MomoConfigs momoConfig;

    public String initialPayment(String orderId, long amount, HttpServletRequest request) {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("partnerCode", momoConfig.getPartnerCode());
        paymentData.put("accessKey", momoConfig.getAccessKey());
        paymentData.put("requestId", orderId);
        paymentData.put("amount", String.valueOf(amount));
        paymentData.put("orderId", orderId);
        paymentData.put("orderInfo", "Payment for order " + orderId);
        paymentData.put("redirectUrl", "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b");
        paymentData.put("ipnUrl", "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b");
        paymentData.put("requestType", "payWithATM");
        paymentData.put("extraData", "");


        // Chuyển đổi paymentData thành JSON
        String rawData = paymentData.entrySet().stream()
                                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                                    .sorted()
                                    .reduce("", (a, b) -> a + "&" + b);

        // Loại bỏ ký tự '&' đầu tiên
        if (rawData.startsWith("&")) {
            rawData = rawData.substring(1);
        }

        String signature = momoConfig.hmacSHA256(momoConfig.getSecretKey(), rawData);
        paymentData.put("signature", signature);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> momoResponse = restTemplate.postForEntity(momoConfig.getEndpoint(), paymentData, Map.class);

        String payUrl = (String) momoResponse.getBody().get("payUrl");
        
        System.err.println(">>>>>>> " + payUrl);
        
        System.err.println(">>>>>>> Momo Response: " + momoResponse);
        
        return (String) momoResponse.getBody().get("payUrl");
    }

    public boolean verifyPaymentResult(Map<String, String> resultData) {
        String receivedSignature = resultData.get("signature");
        resultData.remove("signature");

        String rawData = resultData.toString();  // Định dạng lại dữ liệu trước khi tạo chữ ký
        String calculatedSignature = momoConfig.hmacSHA256(momoConfig.getSecretKey(), rawData);

        return receivedSignature.equals(calculatedSignature);
    }
}
