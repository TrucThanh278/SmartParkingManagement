/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.configs;

import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author trucn
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MomoConfigs {
    
    @Autowired
    private Environment env;
    
    public String getPartnerCode(){
        return env.getProperty("momo.partnerCode");
    }
    
    public String getAccessKey(){
        return env.getProperty("momo.accessKey");
    }
    
    public String getSecretKey() {
        return env.getProperty("momo.secretKey");
    }

    public String getEndpoint() {
        return env.getProperty("momo.endpoint");
    }
    
    public String getReturnUrl(HttpServletRequest request) {
        return getBaseUrl(request) + env.getProperty("momo.returnUrl");
    }

    public String getNotifyUrl(HttpServletRequest request) {
        return getBaseUrl(request) + env.getProperty("momo.notifyUrl");
    }
    
    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }
    
    // Hàm tạo HMAC SHA256 cho các yêu cầu đến MoMo
    public static String hmacSHA256(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac256 = Mac.getInstance("HmacSHA256");
            byte[] hmacKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA256");
            hmac256.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac256.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
    
}
