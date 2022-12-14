package com.hotnerds.badgeroad.user.controller;

import com.hotnerds.badgeroad.user.dto.UserDto;
import com.hotnerds.badgeroad.user.entity.User;
import com.hotnerds.badgeroad.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return "redirect:/login";
    }

    @GetMapping("/info")
    public String getMyUserInfo(@RequestBody String email) {
        UserDto userDto = userService.getUserByEmail(email);
        User user = userService.userDtoToUser(userDto);

        return "info";
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
