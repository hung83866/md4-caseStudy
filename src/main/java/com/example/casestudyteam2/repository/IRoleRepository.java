package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.Role;
import com.example.casestudyteam2.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
