package com.example.back.dto.request.order;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveOrderItemRequestDto {
    private String productId;
    private String title;
    @ElementCollection
    private List<String> productImageList;
    private String lowPrice;
    private String category1;
    private String category2;
    private String category3;
    private String count;
    private String userId;
    private String orderDatetime;
}
