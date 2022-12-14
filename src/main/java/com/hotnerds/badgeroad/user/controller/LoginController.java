package com.hotnerds.badgeroad.user.controller;

import com.hotnerds.badgeroad.user.dto.MemberDto;
import com.hotnerds.badgeroad.user.entity.Member;
import com.hotnerds.badgeroad.user.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "signup";
    }

    @GetMapping("/common")
    public String common() {
        return "common";
    }



    @PostMapping("/login")
    public String loginConfirm(@ModelAttribute("Member") MemberDto member,
                               BindingResult result,
                               Model model) {
        return "redirect:/main";
    }

    // handler method to handle Member registration request


    // handler method to handle register Member form submit request
    @PostMapping("/signup")
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
        return "good";
    }

    @GetMapping("/members")
    public String listRegisteredMembers(Model model){
        List<MemberDto> members = memberService.findAllMembers();
        model.addAttribute("Members", members);
        return "members";
    }
}
