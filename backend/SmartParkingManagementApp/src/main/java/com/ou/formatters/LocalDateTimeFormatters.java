/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

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
    private DateTimeFormatter formatter;
    
    public LocalDateTimeFormatters(String pattern){
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String print(LocalDateTime t, Locale locale) {
        if (t == null) {
            return "";
        }
        return t.format(formatter);
    }

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(text, formatter);
        } catch (Exception e) {
            throw new ParseException("Failed to parse LocalDateTime: " + text, 0);
        }
    }
    
    
    
}
