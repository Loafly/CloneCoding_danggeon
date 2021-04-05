package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserLoginService {
    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
