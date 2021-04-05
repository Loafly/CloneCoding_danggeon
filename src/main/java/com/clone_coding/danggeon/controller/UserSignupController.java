package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.handler.CustomErrorResponse;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserSignupController {
    private final UserLoginService userService;

    public UserSignupController(UserLoginService userService) {
        this.userService = userService;
    }

    //위에 로그인 메서드와 마찬가지로 하려고합니다.
    @PostMapping("/api/signup")
    public Object usersignup(@Valid @RequestBody UserSignupRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomErrorResponse errors = new CustomErrorResponse(errorMessage,status.value());
            return ResponseEntity
                    .status(status)
                    .body(errors);
        }

        User saveUser = userService.save(requestDto);
        return saveUser;

    }
}
