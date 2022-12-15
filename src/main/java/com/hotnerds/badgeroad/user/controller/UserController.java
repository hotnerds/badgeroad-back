package com.hotnerds.badgeroad.user.controller;

import com.hotnerds.badgeroad.user.dto.LoginDto;
import com.hotnerds.badgeroad.user.dto.MemberDto;
import com.hotnerds.badgeroad.user.dto.UserDto;
import com.hotnerds.badgeroad.user.entity.User;
import com.hotnerds.badgeroad.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @RequestBody LoginDto loginDto) {
        userService.loginConfirm(loginDto);

        return "main";
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "signup";
    }



    // handler method to handle Member registration request


    // handler method to handle register Member form submit request
    @PostMapping("/signup")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        UserDto existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }
        userService.saveUser(userService.userDtoToUser(user));
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String listRegisteredMembers(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("Users", users);
        return "users";
    }
}
