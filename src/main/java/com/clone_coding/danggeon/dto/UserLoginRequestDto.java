package com.clone_coding.danggeon.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "3~20자리의 숫자 또는 문자만 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "^.*(?=^.{4,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 4~15자리의 숫자,문자,특수문자로 이루어져야합니다.")
    private String password;
}
