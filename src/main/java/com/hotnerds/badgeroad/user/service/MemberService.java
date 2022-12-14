package com.hotnerds.badgeroad.user.service;

import com.hotnerds.badgeroad.user.dto.MemberDto;
import com.hotnerds.badgeroad.user.dto.MemberLoginDto;
import com.hotnerds.badgeroad.user.entity.Member;

import java.util.List;

public interface MemberService {
    void saveMember(MemberDto memberDto);

    Member findByEmail(String email);

    List<MemberDto> findAllMembers();

    Boolean loginConfirm(MemberLoginDto memberLoginDto);
}