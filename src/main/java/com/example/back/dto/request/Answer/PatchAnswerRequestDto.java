package com.example.back.dto.request.Answer;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchAnswerRequestDto {
    @NotBlank
    private String content;
    @NotBlank
    private String userId;
    @NotNull
    private Long questionId;
}
