package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserLoginController {
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    /*
        UserLoginRequestDto에서의 vaild 조건에 맞지 않으면 메세지와 400에러가 응답으로 반환된다.
     */
    @PostMapping("/api/login")
    public void userLogin(@Valid @RequestBody UserLoginRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError->{
                System.err.println("code : " + objectError.getCode());
                System.err.println("defaultMessage : " + objectError.getDefaultMessage());
                System.err.println("objectName : " + objectError.getObjectName());
            });
            //여기서 에러 메세지를 변수에 담아서 메서지를 리턴하려고 합니다. (메세지는 제가 다시 커스텀해서 리턴하는 것으로 하겠습니다.)
            return;
        }
        //에러가 없으면 서비스에서 저장되어 있는 아이디와 패스워드 비교해서 맞으면 게시물 페이지로 이동하려고 합니다.
    }

    //위에 로그인 메서드와 마찬가지로 하려고합니다.
    @PostMapping("/api/signup")
    public void usersignup(@Valid @RequestBody UserSignupRequestDto requestDto, BindingResult bindingResult) {

    }

}
