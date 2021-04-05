package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserLoginControllerTest {
    @Autowired
    UserLoginController userLoginController;

    @Test
    void loginTest() {
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setPassword("1234");


    }

}