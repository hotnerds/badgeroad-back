package com.hotnerds.badgeroad.controller;

import com.hotnerds.badgeroad.dto.MemberDto;
import com.hotnerds.badgeroad.entity.Member;
import com.hotnerds.badgeroad.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
    private MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("index")
    public String homepage(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle Member registration request
    @GetMapping("signup")
    public String showRegistrationForm(Model model){
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "signup";
    }

    // handler method to handle register Member form submit request
    @PostMapping("/signup/save")
    public String registration(@Valid @ModelAttribute("Member") MemberDto member,
                               BindingResult result,
                               Model model){
        Member existing = memberService.findByEmail(member.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("member", member);
            return "signup";
        }
        memberService.saveMember(member);
        return "redirect:/signup?success";
    }

    @GetMapping("/members")
    public String listRegisteredMembers(Model model){
        List<MemberDto> members = memberService.findAllMembers();
        model.addAttribute("Members", members);
        return "members";
    }
}
