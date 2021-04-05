package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.handler.CreateError;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.UserLoginService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
            return new CreateError().error(errorMessage);
        }

        boolean flag = userService.checkPassword(requestDto);
        if (!flag) {
            return new CreateError().error("비밀번호가 일치하지 않습니다.");
        }

        User saveUser = userService.save(requestDto);
        return saveUser;

    }
}
