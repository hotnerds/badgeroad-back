package com.hotnerds.badgeroad.user.service;

import com.hotnerds.badgeroad.user.dto.MemberDto;
import com.hotnerds.badgeroad.user.dto.MemberLoginDto;
import com.hotnerds.badgeroad.user.entity.Member;
import com.hotnerds.badgeroad.user.entity.Role;
import com.hotnerds.badgeroad.user.repository.MemberRepository;
import com.hotnerds.badgeroad.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;

//    public MemberServiceImpl(MemberRepository MemberRepository,
//                             RoleRepository roleRepository,
//                             PasswordEncoder passwordEncoder) {
//        this.MemberRepository = MemberRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public void saveMember(MemberDto MemberDto) {
        Member member = new Member();
        member.setName(MemberDto.getName());
        member.setEmail(MemberDto.getEmail());

        //encrypt the password once we integrate spring security
        //Member.setPassword(MemberDto.getPassword());
        member.setPassword(MemberDto.getPassword());
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        member.setRoles(List.of(role));
        memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<MemberDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean loginConfirm(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByEmail(memberLoginDto.getEmail());
        return member.getPassword().equals(memberLoginDto.getPassword());
    }

    private MemberDto convertEntityToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        return memberDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}