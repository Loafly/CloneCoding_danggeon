package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;
}
