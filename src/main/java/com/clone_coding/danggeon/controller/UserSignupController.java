package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import com.clone_coding.danggeon.service.UserLoginService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserSignupController {
    private final UserLoginService userService;

    private final UserRepository userRepository;

    public UserSignupController(UserLoginService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //위에 로그인 메서드와 마찬가지로 하려고합니다.
    @PostMapping("/api/signup")
    public User usersignup(@Valid @RequestBody UserSignupRequestDto requestDto, BindingResult bindingResult) {

        System.out.println("createBoards");
        User user = new User(requestDto);
        return userRepository.save(user);

    }

}
