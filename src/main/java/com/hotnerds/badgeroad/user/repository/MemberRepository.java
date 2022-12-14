package com.hotnerds.badgeroad.user.repository;

import com.hotnerds.badgeroad.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);
}
