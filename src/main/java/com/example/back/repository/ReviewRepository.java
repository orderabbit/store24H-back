package com.example.back.repository;

import com.example.back.entity.ReviewEntity;
import com.example.back.repository.resultSet.GetReviewListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {

    @Query(
            value=
                    "SELECT " +
                            "R.review_number AS reviewId, " +
                            "R.write_datetime AS writeDatetime, " +
                            "R.review AS review, " +
                            "R.rates AS rates " +
                            "FROM review AS R " +
                            "INNER JOIN user AS U ON R.user_id = U.user_id " +
                            "ORDER BY writeDatetime DESC",
            nativeQuery = true
    )
    List<GetReviewListResultSet> getReviewList(String productId);

    @Transactional
    void deleteByProductId(String productId);

    List<ReviewEntity> findByUserId(String userId);
    ReviewEntity findByReviewNumber(int reviewNumber);
}
