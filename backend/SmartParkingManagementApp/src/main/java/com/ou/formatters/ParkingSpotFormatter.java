/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

import com.ou.pojo.ParkingSpot;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ADMIN
 */
public class ParkingSpotFormatter implements Formatter<ParkingSpot>{

    @Override
    public String print(ParkingSpot t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public ParkingSpot parse(String parkingSpotID, Locale locale) throws ParseException {
        ParkingSpot p = new ParkingSpot();
        p.setId(Integer.parseInt(parkingSpotID));
        return p;
    }
    
}
