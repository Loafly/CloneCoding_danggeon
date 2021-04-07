package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileUpdateDto {
    @NotBlank(message = "아이디는 필 수 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "3~20자리의 숫자 또는 문자만 가능합니다.")
    private String username;

    @NotBlank(message = "이메일은 필 수 입니다.")
    @Email(message = "email 형식을 지켜주세요.")
    private String email;

    private MultipartFile profile_img;
}
