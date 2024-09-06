/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

import com.ou.pojo.Role;
import com.ou.pojo.Vehicle;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ADMIN
 */
public class VehicleFormatters implements Formatter<Vehicle> {

    @Override
    public String print(Vehicle t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Vehicle parse(String string, Locale locale) throws ParseException {
        Vehicle v = new Vehicle();
        v.setId(Integer.parseInt(string));
        return v;
    }
    
}
//@Override
//public String print(Role role, Locale locale) {
//        return String.valueOf(role.getId());
//    }
//    
//    //Tu template chuyen len server
//    @Override
//public Role parse(String roleId, Locale locale) throws ParseException {
//        Role r = new Role();
//        r.setId(Integer.parseInt(roleId));
//        return r;
//    }
