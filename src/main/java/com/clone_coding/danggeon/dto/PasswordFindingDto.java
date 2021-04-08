package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordFindingDto {
    @NotBlank(message = "username은 필수입니다.")
    private String username;

    @Email(message = "이메일 형식으로 작성해주세요")
    @NotBlank(message = "email은 필수입니다.")
    private String email;
}
