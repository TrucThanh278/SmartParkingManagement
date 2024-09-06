/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

import com.ou.pojo.ParkingSpot;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author trucn
 */
public class LocalDateTimeFormatters implements Formatter<LocalDateTime>{
   
    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeFormatters() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    // Chuyển đổi từ chuỗi sang LocalDateTime
    @Override
    public LocalDateTime parse(String text, Locale locale) {
        return LocalDateTime.parse(text, dateTimeFormatter);
    }

    // Chuyển đổi từ LocalDateTime sang chuỗi
    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.format(dateTimeFormatter);
    }
}
