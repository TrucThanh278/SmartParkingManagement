package com.ou.service.impl;

import com.ou.pojo.Role;
import com.ou.repository.RoleRepository;
import com.ou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    @Transactional
    public Role findRoleById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    @Transactional
    public Role findRoleByName(String name) {
        return roleRepo.findByName(name);
    }
}
