package com.ou.service;

import com.ou.pojo.Role;

public interface RoleService {
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
