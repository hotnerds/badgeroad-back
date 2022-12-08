package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.dto.MemberDto;
import com.hotnerds.badgeroad.entity.Member;

import java.util.List;

public interface MemberService {
    void saveMember(MemberDto memberDto);

    Member findByEmail(String email);

    List<MemberDto> findAllMembers();
}