package com.example.back.service;

import com.example.back.dto.request.Answer.PatchAnswerRequestDto;
import com.example.back.dto.request.Answer.PostAnswerRequestDto;
import com.example.back.dto.response.Answer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    ResponseEntity<? super GetAnswerResponseDto> getAnswer(Long questionId);

    ResponseEntity<? super PostAnswerResponseDto> postAnswer(PostAnswerRequestDto dto);

    ResponseEntity<? super PatchAnswerResponseDto> patchAnswer(PatchAnswerRequestDto dto, Long answerId);

    ResponseEntity<? super DeleteAnswerResponseDto> deleteAnswer(Long AnswerId);

    ResponseEntity<? super GetAllAnswerResponseDto> getAllAnswers();

}
