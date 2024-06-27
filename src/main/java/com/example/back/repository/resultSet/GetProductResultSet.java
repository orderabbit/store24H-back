package com.example.back.repository.resultSet;

import org.springframework.stereotype.Repository;

@Repository
public interface GetProductResultSet {
    String getProductId();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getUserId();
    String getLowPrice();
    String getCategory1();
    String getCategory2();
    String getCategory3();
}
