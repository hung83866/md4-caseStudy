package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Role;
import com.example.casestudyteam2.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
