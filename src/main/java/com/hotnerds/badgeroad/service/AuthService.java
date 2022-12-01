package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.domain.Member;
import com.hotnerds.badgeroad.domain.UserDetails;
import com.hotnerds.badgeroad.domain.UserDetailsImpl;
import com.hotnerds.badgeroad.dto.JwtRequestDto;
import com.hotnerds.badgeroad.dto.MemberSignupRequestDto;
import com.hotnerds.badgeroad.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String signup(MemberSignupRequestDto request) {
        Optional<Member> existMember = memberRepository.findByEmail(request.getEmail());

        if (existMember.isEmpty()) return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getEmail();
    }

    public String login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        return principal.getUsername();
    }
}
