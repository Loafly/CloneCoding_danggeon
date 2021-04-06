package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;

import com.clone_coding.danggeon.handler.CreateError;
import com.clone_coding.danggeon.handler.CustomMessageResponse;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import com.clone_coding.danggeon.response.TokenResponse;
import com.clone_coding.danggeon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserLoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserLoginController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/api/users")
    public List<User> getUser(){
        return userService.findAll();
    }


    /*
        UserLoginRequestDto에서의 vaild 조건에 맞지 않으면 메세지와 400에러가 응답으로 반환된다.
     */
    @PostMapping("/api/login")
    public Object userLogin(@Valid @RequestBody UserLoginRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomMessageResponse errors = new CustomMessageResponse(errorMessage,status.value());
            return ResponseEntity
                    .status(status)
                    .body(errors);
        }
        if(!userService.checkUsernameAndPassword(requestDto)) {
            return new CreateError().error("비밀번호가 일치하지 않습니다.");
        }else {
            String token = userService.createToken(requestDto);
            return ResponseEntity.ok().body(new TokenResponse(token, "bearer"));
        }

    }


}
