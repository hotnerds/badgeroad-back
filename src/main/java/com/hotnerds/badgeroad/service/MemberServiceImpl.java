package com.hotnerds.badgeroad.service;

import com.hotnerds.badgeroad.dto.MemberDto;
import com.hotnerds.badgeroad.entity.Member;
import com.hotnerds.badgeroad.entity.Role;
import com.hotnerds.badgeroad.repository.MemberRepository;
import com.hotnerds.badgeroad.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        Member Member = new Member();
        Member.setName(MemberDto.getFirstName() + " " + MemberDto.getLastName());
        Member.setEmail(MemberDto.getEmail());

        //encrypt the password once we integrate spring security
        //Member.setPassword(MemberDto.getPassword());
        Member.setPassword(passwordEncoder.encode(MemberDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        Member.setRoles(Arrays.asList(role));
        MemberRepository.save(Member);
    }

    @Override
    public Member findByEmail(String email) {
        return MemberRepository.findByEmail(email);
    }

    @Override
    public List<MemberDto> findAllMembers() {
        List<Member> Members = MemberRepository.findAll();
        return Members.stream().map((Member) -> convertEntityToDto(Member))
                .collect(Collectors.toList());
    }

    private MemberDto convertEntityToDto(Member Member) {
        MemberDto memberDto = new MemberDto();
        String[] name = Member.getName().split(" ");
        memberDto.setFirstName(name[0]);
        memberDto.setLastName(name[1]);
        memberDto.setEmail(Member.getEmail());
        return memberDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}