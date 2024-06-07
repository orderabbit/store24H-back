package com.example.back.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveProductRequestDto {

    private String title;
    private String link;
    private String image;
    private String lowPrice;
    private int count;
    private String category1;
    private String category2;
}