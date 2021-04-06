package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/profile")
    public Object getUserFromToken(HttpServletRequest request) {
        String username = (String)request.getAttribute("username");
        User user = userService.findByName(username);
        return user;
    }
}

