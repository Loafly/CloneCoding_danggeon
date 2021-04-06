package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.dto.UserCheckNameDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public User save(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        User user = new User(username,password,email);

        User saveUser = userRepository.save(user);
        return saveUser;
    }
    //회원가입 비밀번호 체크 유효성 검사.
    public boolean checkPassword(UserSignupRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getPwcheck())) {
            return false;
        }
        return true;
    }

    //username 존재하는지 확인하기
    public boolean existByUsername(UserCheckNameDto userCheckNameDto) {
        boolean flag = userRepository.existsByUsername(userCheckNameDto.getUsername());
        return flag;
    }

    //회원목록 조회
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
