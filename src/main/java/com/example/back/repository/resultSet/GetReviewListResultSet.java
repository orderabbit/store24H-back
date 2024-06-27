package com.example.back.repository.resultSet;

import org.springframework.stereotype.Repository;

@Repository
public interface GetReviewListResultSet {
    String getReviewId();
    String getRates();
    String getNickname();
    String getWriteDatetime();
    String getReview();
}
