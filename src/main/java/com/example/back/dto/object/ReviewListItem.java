package com.example.back.dto.object;


import com.example.back.repository.resultSet.GetReviewListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListItem {

    private String reviewNumber;
    private String userId;
    private String rates;
    private String writeDatetime;
    private String review;

    public ReviewListItem(GetReviewListResultSet resultSet){
        this.writeDatetime = resultSet.getWriteDatetime();
        this.rates = resultSet.getRates();
        this.review = resultSet.getReview();
        this.reviewNumber = resultSet.getReviewId();
    }

    public static List<ReviewListItem> copyList(List<GetReviewListResultSet> resultSets){
        List<ReviewListItem> list = new ArrayList<>();
        for(GetReviewListResultSet resultSet: resultSets){
            ReviewListItem reviewListItem = new ReviewListItem(resultSet);
            list.add(reviewListItem);
        }
        return list;
    }
}
