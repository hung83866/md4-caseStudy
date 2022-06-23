package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.Role;
import com.example.casestudyteam2.model.RoleName;
import com.example.casestudyteam2.repository.IRoleRepository;
import com.example.casestudyteam2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
