package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<Users> findAll();
    Users findById(Long id);
    Optional<Users> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    boolean checkLogin(Users users);
    List<Users> findByNameCon(String name);
}
