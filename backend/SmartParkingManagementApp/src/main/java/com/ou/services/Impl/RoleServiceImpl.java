/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.ou.pojo.Role;
import com.ou.repositories.RoleRepository;
import com.ou.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.getRoles();
    }

    @Override
    public Role findRoleById(Long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return this.roleRepository.getRoleByName(name);
    }
    
}
