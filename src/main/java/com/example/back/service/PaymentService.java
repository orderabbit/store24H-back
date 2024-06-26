package com.example.back.service;

import com.example.back.dto.response.payment.GetPaymentResponseDto;
import com.example.back.dto.response.payment.PaymentResponseDto;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    JSONObject confirmPayment(String jsonBody) throws Exception;
    ResponseEntity<? super PaymentResponseDto> savePaymentInfo(JSONObject paymentInfo);
    ResponseEntity<? super GetPaymentResponseDto> getPaymentInfo(String orderId);
}
