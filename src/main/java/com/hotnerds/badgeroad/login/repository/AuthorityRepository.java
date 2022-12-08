package com.hotnerds.badgeroad.login.repository;

import com.hotnerds.badgeroad.login.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
