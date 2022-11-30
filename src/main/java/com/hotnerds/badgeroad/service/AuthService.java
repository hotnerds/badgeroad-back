package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.domain.Member;
import com.hotnerds.badgeroad.dto.MemberSignupRequestDto;
import com.hotnerds.badgeroad.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(MemberSignupRequestDto request) {
        boolean existMember = memberRepository.existById(request.getEmail());

        if (existMember) return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getEmail();
    }
}
