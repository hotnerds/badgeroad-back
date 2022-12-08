package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.dto.MemberDto;
import com.hotnerds.badgeroad.entity.Member;
import com.hotnerds.badgeroad.entity.Role;
import com.hotnerds.badgeroad.repository.MemberRepository;
import com.hotnerds.badgeroad.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository MemberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

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
        member.setPassword(passwordEncoder.encode(MemberDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        member.setRoles(List.of(role));
        MemberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return MemberRepository.findByEmail(email);
    }

    @Override
    public List<MemberDto> findAllMembers() {
        List<Member> members = MemberRepository.findAll();
        return members.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
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