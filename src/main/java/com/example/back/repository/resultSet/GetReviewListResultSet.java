package com.example.back.repository.resultSet;

import org.springframework.stereotype.Repository;

@Repository
public interface GetReviewListResultSet {
    String getNickname();
    String getWriteDatetime();
    String getContent();
}
