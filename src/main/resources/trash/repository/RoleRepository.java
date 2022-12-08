package com.hotnerds.badgeroad.repository;

import com.hotnerds.badgeroad.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
