package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<Users> findByNameContaining(String name);
}
