package com.example.backend.domain.repository;

import com.example.backend.domain.entity.role.Role;
import com.example.backend.domain.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleName(RoleName roleName);

    boolean existsByRoleName(RoleName roleName);
}
