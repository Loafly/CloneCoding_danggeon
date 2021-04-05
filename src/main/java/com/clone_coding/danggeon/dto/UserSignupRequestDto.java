package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
    @NotBlank(message = "이름은 필수 입력입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*",message = "username은 숫자 또는 문자만 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "^.*(?=^.{4,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 4~15자리의 숫자,문자,특수문자로 이루어져야합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "email 형식을 지켜주세요.")
    private String email;

}
