package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.PasswordFindingDto;
import com.clone_coding.danggeon.dto.UsernameFindingDto;
import com.clone_coding.danggeon.handler.CustomMessageResponse;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UsernameFindingController {
    private final UserService userService;

    public UsernameFindingController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/usernamefind")
    public Object findUsername(@Valid @RequestBody UsernameFindingDto usernameFindingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomMessageResponse errors = new CustomMessageResponse(errorMessage,status.value());
            return ResponseEntity
                    .status(status)
                    .body(errors);
        }
        User userByEmail = userService.findByEmail(usernameFindingDto.getEmail());
        return userByEmail.getUsername();

    }

    @PostMapping("/api/passwordfind")
    public Object findPassword(@Valid @RequestBody PasswordFindingDto passwordFindingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomMessageResponse errors = new CustomMessageResponse(errorMessage,status.value());
            return ResponseEntity
                    .status(status)
                    .body(errors);
        }
        return userService.findPw(passwordFindingDto);
    }
}
