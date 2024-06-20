package com.example.back.controller;

import com.example.back.dto.request.cart.SaveCartRequestDto;
import com.example.back.dto.response.cart.DeleteCartResponseDto;
import com.example.back.dto.response.cart.ListCartResponseDto;
import com.example.back.dto.response.cart.SaveCartResponseDto;
import com.example.back.dto.response.product.GetSearchProductResponseDto;
import com.example.back.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://3.35.30.191:3000")
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/search")
    public ResponseEntity<? super GetSearchProductResponseDto> searchProducts(
            @RequestParam String keyword) {
        ResponseEntity<? super GetSearchProductResponseDto> response = cartService.searchProductsFromApi(keyword);
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<? super SaveCartResponseDto> saveProduct(
            @RequestBody SaveCartRequestDto dto,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super SaveCartResponseDto> response = cartService.saveProducts(dto, userId);
        return response;
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<? super ListCartResponseDto> getUserProductList(
            @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super ListCartResponseDto> response = cartService.getUserCartList(userId);
        return response;
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<? super DeleteCartResponseDto> deleteProduct(
            @PathVariable("productId") Long productId
    ) {
        ResponseEntity<? super DeleteCartResponseDto> response = cartService.deleteProduct(productId);
        return response;
    }
}
