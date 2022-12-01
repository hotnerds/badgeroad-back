package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.domain.Member;
import com.hotnerds.badgeroad.domain.UserDetailsImpl;
import com.hotnerds.badgeroad.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{

    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 사용자 입니다."));

        return (UserDetails) new UserDetailsImpl(member);
    }
}
