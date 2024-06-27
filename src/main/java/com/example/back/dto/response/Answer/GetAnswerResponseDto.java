package com.example.back.dto.response.Answer;


import com.example.back.common.ResponseCode;
import com.example.back.common.ResponseMessage;
import com.example.back.dto.response.ResponseDto;
import com.example.back.entity.AnswerEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetAnswerResponseDto extends ResponseDto {

    private List<AnswerDto> answer;

    public GetAnswerResponseDto(List<AnswerEntity> answerEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.answer = answerEntities.stream()
                .map(AnswerDto::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetAnswerResponseDto> success(List<AnswerEntity> answerEntities) {
        GetAnswerResponseDto responseBody = new GetAnswerResponseDto(answerEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistAnswer() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_ANSWER, ResponseMessage.NOT_EXISTED_ANSWER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @Getter
    public static class AnswerDto {
        private Long answerId;
        private String content;
        private String userId;
        private LocalDateTime createdAt;

        public AnswerDto(AnswerEntity answerEntity) {
            this.answerId = answerEntity.getAnswerId();
            this.content = answerEntity.getContent();
            this.userId = answerEntity.getUserId();
            this.createdAt = answerEntity.getCreatedAt();
        }
    }
}
