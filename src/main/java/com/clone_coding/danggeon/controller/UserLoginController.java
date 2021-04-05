package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.Header;
import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.service.UserLoginService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserLoginController {
    private final UserLoginService userService;

    public UserLoginController(UserLoginService userService) {
        this.userService = userService;
    }
    /*
        UserLoginRequestDto에서의 vaild 조건에 맞지 않으면 메세지와 400에러가 응답으로 반환된다.
     */
    @PostMapping("/api/login")
    public Object userLogin(@Valid @RequestBody UserLoginRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String[] errors = new String[allErrors.size()];
            for (int i=0; i<allErrors.size(); i++) {
                errors[i] = String.valueOf(allErrors.get(i).getDefaultMessage());
            }
            return Header.ERROR(errors);
        }
        return Header.OK();
    }

}
