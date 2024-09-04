/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

import com.ou.pojo.Role;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author trucn
 */
public class RoleFormatters implements Formatter<Role>{

    //Tu server do ra template
    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }
    
    //Tu template chuyen len server
    @Override
    public Role parse(String roleId, Locale locale) throws ParseException {
        Role r = new Role();
        r.setId(Integer.parseInt(roleId));
        return r;
    }
    
    
}
