package com.example.back.dto.response.product;


import com.example.back.common.ResponseCode;
import com.example.back.common.ResponseMessage;
import com.example.back.dto.response.ResponseDto;
import com.example.back.entity.ProductEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class DeleteProductResponseDto extends ResponseDto{

    private DeleteProductResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteProductResponseDto> success(){
        DeleteProductResponseDto result = new DeleteProductResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedProduct(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
