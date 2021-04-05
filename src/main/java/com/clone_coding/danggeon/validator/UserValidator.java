package com.clone_coding.danggeon.validator;

import com.clone_coding.danggeon.dto.UserLoginRequestDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserLoginRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserLoginRequestDto userLoginRequestDto = (UserLoginRequestDto) target;
        //username 유효성 체크
        String username = userLoginRequestDto.getUsername();
        if (username == null || username.isEmpty() || username.equals("")){
            errors.rejectValue("usernameError","id_required");
        }

        //password 유효성 체크
        String password = userLoginRequestDto.getPassword();
        if (password == null || password.isEmpty() || password.equals("")) {
            errors.rejectValue("passwordError","password_required");
        }

    }
}
