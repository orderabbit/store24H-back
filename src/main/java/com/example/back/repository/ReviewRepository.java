package com.example.back.repository;

import com.example.back.entity.ReviewEntity;
import com.example.back.repository.resultSet.GetReviewListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {


    boolean existsByReviewNumber (int reviewNumber);
    ReviewEntity findByReviewNumber(int reviewNumber);

    @Query(
            value = "SELECT " +
                                "R.review_number AS review_number, " +
                                "R.write_datetime AS writeDatetime, " +
                                "R.review AS review, " +
                                "R.rates AS rates " +
                                "FROM review AS R " +
                                "INNER JOIN product AS P ON R.product_id = P.product_id " +
                                "ORDER BY R.write_datetime DESC",
            nativeQuery = true
    )
    List<GetReviewListResultSet> getReviewList(String productId);

    @Transactional
    void deleteByProductId(String productId);

    @Transactional
    void deleteByUserId(String userId);

}
