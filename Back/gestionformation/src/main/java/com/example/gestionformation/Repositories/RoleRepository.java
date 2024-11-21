package com.example.gestionformation.Repositories;

import java.util.Optional;

import com.example.gestionformation.Entities.EnumRole;
import com.example.gestionformation.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(EnumRole name);
}
