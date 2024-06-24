package com.example.back.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {

    @NotBlank
    private String review;
    private int rates;
}
