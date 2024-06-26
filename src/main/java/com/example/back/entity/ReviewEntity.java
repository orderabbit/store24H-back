package com.example.back.entity;

import com.example.back.dto.request.product.PostReviewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "review")
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewNumber;
    private String review;
    private String writeDatetime;
    private String userId;
    private String productId;
    private int rates;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private ProductEntity targetProduct;

    private List<String> likedUserList;
    private List<String> dislikedUserList;

//    @OneToMany(mappedBy = "likedReviewEntity")
//    private List<UserEntity> likedUserList;
//    @OneToMany(mappedBy = "dislikedReviewEntity")
//    private List<UserEntity> dislikedUserList;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    public ReviewEntity(PostReviewRequestDto dto, String productId, String userId) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.review = dto.getReview();
        this.writeDatetime = writeDatetime;
        this.userId = userId;
        this.productId = productId;

        rates = dto.getRates();
        likedUserList = new ArrayList<>();
        dislikedUserList = new ArrayList<>();
    }
}
