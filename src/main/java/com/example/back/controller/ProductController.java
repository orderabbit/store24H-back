package com.example.back.controller;

import com.example.back.dto.request.product.PatchProductRequestDto;
import com.example.back.dto.request.product.PostProductRequestDto;
import com.example.back.dto.request.product.PostReviewRequestDto;
import com.example.back.dto.response.product.*;
import com.example.back.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<? super PostProductResponseDto> postProduct(
            @RequestBody @Valid PostProductRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostProductResponseDto> response = productService.postProduct(requestBody, userId);
        return response;
    }

    @PostMapping("/review")
    public ResponseEntity<? super PostReviewResponseDto> postReview(
            @RequestBody @Valid PostReviewRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostReviewResponseDto> response = productService.postReview(requestBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetProductListResponseDto> getProductList() {
        ResponseEntity<? super GetProductListResponseDto> response = productService.getProductList();
        return response;
    }

    @GetMapping("/{productId}/review-list")
    public ResponseEntity<? super GetReviewResponseDto> getReviewList(
            @PathVariable("productId") String productId
    ) {
        ResponseEntity<? super GetReviewResponseDto> reponse = productService.getReviewList(productId);
        return reponse;
    }

    @GetMapping(value = {"/search"})
    public ResponseEntity<? super SearchProductResponseDto> getSearchBoardList(
            @RequestParam String keyword
    ) {
        ResponseEntity<? super SearchProductResponseDto> response = productService.getSearchProductList(keyword);
        return response;
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<? super GetProductResponseDto> getProduct(
            @PathVariable("productId") String productId,
            @RequestParam(value = "type", required = false, defaultValue = "primary") String type
    ) {
        ResponseEntity<? super GetProductResponseDto> response = productService.getProduct(productId, type);
        return response;
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<? super PatchProductResponseDto> patchProduct(
            @RequestBody @Valid PatchProductRequestDto requestBody,
            @PathVariable("productId") String productId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchProductResponseDto> response = productService.patchProduct(requestBody, productId, userId);
        return response;
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<? super DeleteProductResponseDto> deleteProduct(
            @PathVariable("productId") String productId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteProductResponseDto> response = productService.deleteProduct(productId, userId);
        return response;
    }

    @DeleteMapping("/review/delete/{reviewNumber}")
    public ResponseEntity<? super DeleteReviewResponseDto> deleteReview(
            @PathVariable("reviewNumber") Integer reviewNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteReviewResponseDto> response = productService.deleteReview(reviewNumber, userId);
        return response;
    }
}
