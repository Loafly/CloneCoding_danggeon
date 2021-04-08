package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.bcrypt.EncryptHelper;
import com.clone_coding.danggeon.dto.PasswordFindingDto;
import com.clone_coding.danggeon.dto.UserCheckNameDto;
import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import com.clone_coding.danggeon.dto.UserSignupRequestDto;
import com.clone_coding.danggeon.handler.JwtTokenProvider;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import com.clone_coding.danggeon.utils.MailUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    //email로 아이디 찾기
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //비밀번호 찾기
    @Transactional
    public String findPw(PasswordFindingDto passwordFindingDto) {
        String result = null;

        //회원 정보 불러오기
        User userByEmail = userRepository.findByEmail(passwordFindingDto.getEmail());

        //가입된 이메일이 존재한다면 이메일 발송
        if (userByEmail != null) {
            String tempPw = UUID.randomUUID().toString();

            //uuid 너무 길어서 잘라주었다.
            tempPw = tempPw.substring(0,10);

            System.out.println("임시 비밀번호 : " + tempPw);

            //user객체에 임시 비밀번호 담기 (여기서 왜 암호화를 안해줬냐면 메일을 보낼때 암호화한 패스워드를 보내주면 안되니깐)
            userByEmail.setPassword(tempPw);

            //메일 전송
            MailUtil mail = new MailUtil();
            try {
                mail.sendMail(userByEmail);
            } catch (Exception e) {
                System.out.println("메일 보내기 오류입니다.");
            }

            //회원 비밀번호를 암호화하여 user객체에 다시 저장
            String securePw = encryptHelper.encrypt(userByEmail.getPassword());
            userByEmail.setPassword(securePw);

            result = "Success";

        } else
            result = "Fail";

        return result;
    }

}
