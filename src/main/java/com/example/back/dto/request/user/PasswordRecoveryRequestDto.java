package com.example.back.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordRecoveryRequestDto {
    @NotBlank(message = "이메일은 필수 항목입니다")
    @Email(message = "유효한 이메일 방식을 입력해주세요")
    private String email;
}
