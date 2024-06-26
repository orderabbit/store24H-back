package com.example.back.entity;

import com.example.back.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    @Email
    @Column(name = "email")
    private String email;
    private String nickname;
    private String profileImage;
    private String provider;
    private boolean socialUser;
    private String type;
    private String role;

    private List<String> likedReviewList;
    private List<String> dislikedReviewList;

//    @OneToMany(mappedBy = "likedUserEntity")
//    private List<ReviewEntity> likedReviewList;
//    @OneToMany(mappedBy = "likedUserEntity")
//    private List<ReviewEntity> dislikedReviewList;

    public UserEntity(SignUpRequestDto dto){
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.socialUser = false;
        this.type = "app";
        this.role = "ROLE_USER";
        this.likedReviewList = new ArrayList<>();
        this.dislikedReviewList = new ArrayList<>();
    }

    public UserEntity(String userId, String email, String type, String nickname, String profileImage){
        this.userId = userId;
        this.password = "Password";
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.socialUser = true;
        this.type = type;
        this.role = "ROLE_USER";
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
}
