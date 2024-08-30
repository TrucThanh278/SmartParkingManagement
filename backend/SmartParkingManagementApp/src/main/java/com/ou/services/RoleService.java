/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.Role;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface RoleService {
    public List<Role> getRoles();
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
