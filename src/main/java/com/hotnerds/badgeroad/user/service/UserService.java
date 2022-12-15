package com.hotnerds.badgeroad.user.service;

import com.hotnerds.badgeroad.user.dto.LoginDto;
import com.hotnerds.badgeroad.user.dto.UserDto;
import com.hotnerds.badgeroad.user.entity.User;
import com.hotnerds.badgeroad.user.exception.DuplicateMemberException;
import com.hotnerds.badgeroad.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto findByEmail(String email) {
        return UserDto.from(userRepository.findByEmail(email).orElse(null));
    }

    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Boolean loginConfirm(LoginDto loginDto) {

        UserDto userDto = findByEmail(loginDto.getEmail());
        return loginDto.getPassword().equals(userDto.getPassword());
    }
}
