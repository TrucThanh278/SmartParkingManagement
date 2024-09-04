/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.Role;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface RoleRepository {
    List<Role> getRoles();
    Role getRoleByName(String name);
    Role findById(Long id);
}
