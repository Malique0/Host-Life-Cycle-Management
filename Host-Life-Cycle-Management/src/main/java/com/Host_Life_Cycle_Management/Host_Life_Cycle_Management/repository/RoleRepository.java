package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.repository;

import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.Role;
import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
