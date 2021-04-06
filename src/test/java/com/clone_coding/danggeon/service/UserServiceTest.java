package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.bcrypt.EncryptHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    EncryptHelper encryptHelper;

    @Test
    void 비밀번호_검증() {
        String password = "abcd11!!";
        String encrypt = encryptHelper.encrypt(password);
        System.out.println(password + " " + encrypt);
        Assertions.assertTrue(encryptHelper.isMatch(password, encrypt));
    }

}