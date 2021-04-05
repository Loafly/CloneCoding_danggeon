package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.service.UserLoginService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserSignupController {
    private final UserLoginService userService;

    public UserSignupController(UserLoginService userService) {
        this.userService = userService;
    }

    //위에 로그인 메서드와 마찬가지로 하려고합니다.
    @PostMapping("/api/signup")
    public void usersignup(@Valid @RequestBody UserSignupRequestDto requestDto, BindingResult bindingResult) {

    }
}
