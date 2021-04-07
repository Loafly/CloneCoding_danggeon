package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.bcrypt.EncryptHelper;
import com.clone_coding.danggeon.dto.UserCheckNameDto;
import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.handler.JwtTokenProvider;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final EncryptHelper encryptHelper;
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

//    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, EncryptHelper encryptHelper) {
        this.userRepository = userRepository;
        this.encryptHelper = encryptHelper;
    }


    @Transactional
    public User save(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = encryptHelper.encrypt(requestDto.getPassword());
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
    //로그인 회원아이디를 찾고 저장된 아이디의 비밀번호와 유효성 검사
    public boolean checkUsernameAndPassword(UserLoginRequestDto requestDto) {
        User findUsername = userRepository.findByUsername(requestDto.getUsername());
        if (encryptHelper.isMatch(requestDto.getPassword(), findUsername.getPassword())){
            return true;
        }else
            return false;
    }

    public String createToken(UserLoginRequestDto requestDto) {
        return jwtTokenProvider.createToken(requestDto.getUsername());
    }

    public User findByName(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }


}
