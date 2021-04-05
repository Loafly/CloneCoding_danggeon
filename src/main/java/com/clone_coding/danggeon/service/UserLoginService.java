package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserLoginService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public User save(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        User user = new User(username,password,email);

        User saveUser = userRepository.save(user);
        return saveUser;
    }
}
