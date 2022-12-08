package com.hotnerds.badgeroad.repository;

import com.hotnerds.badgeroad.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);
}
