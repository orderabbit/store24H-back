package com.example.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String userId;
    private String productId;
    private String image;
    private String imageType;

    public ImageEntity(String productId, String image, String userId, String imageType){
        this.userId = userId;
        this.productId = productId;
        this.image = image;
        this.imageType = imageType;
    }
}
