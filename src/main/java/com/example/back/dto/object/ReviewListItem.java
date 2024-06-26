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
    private String review_number;
    private String user_id;
    private String writeDatetime;
    private String review;
    private int rates;

    public ReviewListItem(GetReviewListResultSet resultSet){
        this.review_number = resultSet.getReview_number();
        this.user_id = resultSet.getUser_id();
        this.writeDatetime = resultSet.getWriteDatetime();
        this.review = resultSet.getReview();
        this.rates = resultSet.getRates();
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
