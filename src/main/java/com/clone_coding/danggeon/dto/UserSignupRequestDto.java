package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
    @NotBlank(message = "이름은 필수 입력입니다.")
    @Length(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String password;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "email 형식을 지켜주세요.")
    private String email;

}
