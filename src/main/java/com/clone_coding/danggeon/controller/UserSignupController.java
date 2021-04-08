package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserCheckNameDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.handler.CreateError;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.UserService;
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
    private final UserService userService;

    public UserSignupController(UserService userService) {
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

    //중복회원 검사
    @PostMapping("/api/checkusername")
    public Object checkUserName(@RequestBody UserCheckNameDto userCheckNameDto) {
        if(userService.existByUsername(userCheckNameDto)) {
            return new CreateError().error("이미 존재하는 회원입니다.");
        }
        return new CreateError().ok("사용가능한 아이디입니다.");
    }
}
