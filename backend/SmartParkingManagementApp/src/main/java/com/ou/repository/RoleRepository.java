package com.ou.repository;

import com.ou.pojo.Role;

public interface RoleRepository {
    Role findById(Long id);
    Role findByName(String name);
}
