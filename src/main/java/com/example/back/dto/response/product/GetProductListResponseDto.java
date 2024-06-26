package com.example.back.dto.response.product;

import com.example.back.common.ResponseCode;
import com.example.back.common.ResponseMessage;
import com.example.back.dto.response.ResponseDto;
import com.example.back.entity.ProductListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetProductListResponseDto extends ResponseDto {

    private List<ProductListViewEntity> items;

    public GetProductListResponseDto(List<ProductListViewEntity> items) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.items = items;
    }

    public static ResponseEntity<GetProductListResponseDto> success(List<ProductListViewEntity> products){
        GetProductListResponseDto responseBody = new GetProductListResponseDto(products);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistProduct() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_PRODUCT, ResponseMessage.NOT_EXISTED_PRODUCT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
