package com.example.back.service.implement;

import com.example.back.dto.request.product.PatchProductRequestDto;
import com.example.back.dto.request.product.PostProductRequestDto;
import com.example.back.dto.request.product.PostReviewRequestDto;
import com.example.back.dto.response.ResponseDto;
import com.example.back.dto.response.product.*;
import com.example.back.dto.response.user.GetUserResponseDto;
import com.example.back.entity.*;
import com.example.back.repository.*;
import com.example.back.repository.resultSet.GetProductResultSet;
import com.example.back.repository.resultSet.GetReviewListResultSet;
import com.example.back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductListViewRepository productListViewRepository;
    private final ReviewRepository reviewRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super GetProductResponseDto> getProduct(String productId, String type) {

        GetProductResultSet resultSet = null;
        List<ImageEntity> primaryImages = new ArrayList<>();
        List<ImageEntity> secondaryImages = new ArrayList<>();

        try {
            resultSet = productRepository.getProduct(productId);
            if (resultSet == null) return GetProductResponseDto.notExistProduct();
            primaryImages = imageRepository.findByProductIdAndImageType(productId, "primary");
            secondaryImages = imageRepository.findByProductIdAndImageType(productId, "secondary");

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetProductResponseDto.success(resultSet, primaryImages, secondaryImages);
    }

    @Override
    public ResponseEntity<? super GetReviewResponseDto> getReviewList(String productId) {

        List<GetReviewListResultSet> resultSets = new ArrayList<>();

        try {
            boolean existedProduct = productRepository.existsByProductId(productId);
            if (!existedProduct) return GetReviewResponseDto.notExistProduct();

            resultSets = reviewRepository.getReviewList(productId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReviewResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super SearchProductResponseDto> getSearchProductList(String keyword) {
        List<ProductListViewEntity> productListViewEntities = new ArrayList<>();

        try{
            productListViewEntities = productListViewRepository.findByCategory1ContainingOrCategory2ContainingOrCategory3Containing(keyword, keyword, keyword);
            if(productListViewEntities.size() == 0) return SearchProductResponseDto.notExistedProduct();

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SearchProductResponseDto.success(productListViewEntities);
    }

    @Override
    public ResponseEntity<? super PostProductResponseDto> postProduct(PostProductRequestDto dto, String userId) {

        try {
            boolean existedUserId = userRepository.existsByUserId(userId);
            if (!existedUserId) return PostProductResponseDto.notExistUser();

            boolean existedProductId = productRepository.existsByProductId(dto.getProductId());
            if(existedProductId) return PostProductResponseDto.duplicateProductId();

            ProductEntity productEntity = new ProductEntity(dto, userId);
            productRepository.save(productEntity);

            String productId = productEntity.getProductId();

            List<String> productImageList = dto.getProductImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : productImageList) {
                ImageEntity imageEntity = new ImageEntity(productId, image, userId, "primary");
                imageEntities.add(imageEntity);
            }

            List<String> secondaryProductImageList = dto.getSecondaryProductImageList();
            for (String image : secondaryProductImageList) {
                ImageEntity imageEntity = new ImageEntity(productId, image, userId, "secondary");
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostProductResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProductResponseDto> patchProduct(PatchProductRequestDto dto, String productId, String userId) {
        try{
            ProductEntity productEntity = productRepository.findByProductId(productId);
            if(productEntity == null) return PatchProductResponseDto.notExistProduct();

            boolean existedUser = userRepository.existsByUserId(userId);
            if(!existedUser) return PatchProductResponseDto.notExistUser();

            String writerId = productEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if(!isWriter) return PatchProductResponseDto.notPermission();

            productEntity.patchProduct(dto);
            productRepository.save(productEntity);

            imageRepository.deleteByProductId(productId);
            List<String> productImageList = dto.getProductImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: productImageList) {
                ImageEntity imageEntity = new ImageEntity(productId, image, userId, "primary");
                imageEntities.add(imageEntity);
            }

            List<String> secondaryProductImageList = dto.getSecondaryProductImageList();
            for (String image : secondaryProductImageList) {
                ImageEntity imageEntity = new ImageEntity(productId, image, userId, "secondary");
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchProductResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostReviewResponseDto> postReview(PostReviewRequestDto dto, String productId, String userId) {
        try {
            ProductEntity productEntity = productRepository.findByProductId(productId);
            if (productEntity == null) return PostReviewResponseDto.notExistProduct();

            boolean existUser = userRepository.existsByUserId(userId);
            if (!existUser) return PostReviewResponseDto.notExistUser();

            ReviewEntity reviewEntity = new ReviewEntity(dto, productId, userId);
            reviewRepository.save(reviewEntity);

            productRepository.save(productEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostReviewResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteProductResponseDto> deleteProduct(String productId, String userId) {
        try{

            boolean existedUser = userRepository.existsByUserId(userId);
            if(!existedUser) return DeleteProductResponseDto.notExistedUser();

            ProductEntity productEntity = productRepository.findByProductId(productId);
            if(productEntity == null) return DeleteProductResponseDto.notExistedProduct();

            String writerId = productEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if(!isWriter) return DeleteProductResponseDto.notPermission();

            imageRepository.deleteByProductId(productId);
            reviewRepository.deleteByProductId(productId);
            productRepository.delete(productEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteProductResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchReviewResponseDto> patchReview(int reviewNumber, String userId, boolean feels) {
        try {
            boolean existedReview = reviewRepository.existsByReviewNumber(reviewNumber);
            if(!existedReview) return GetReviewResponseDto.notExistProduct();
            ReviewEntity reviewEntity = reviewRepository.findByReviewNumber(reviewNumber);

            boolean existedUser = userRepository.existsByUserId(userId);
            if(!existedUser) return GetUserResponseDto.notExistUser();
            UserEntity userEntity = userRepository.findByUserId(userId);

            boolean existProduct = productRepository.existsByProductId(reviewEntity.getProductId());
            if(!existProduct) return GetProductResponseDto.notExistProduct();
            ProductEntity productEntity = productRepository.findByProductId(reviewEntity.getProductId());

            //List<ReviewEntity> feelsList;
            List<String> userFeelsList;
            List<String> reviewFeelsList;
            if(feels) {
                userFeelsList = userEntity.getLikedReviewList();
                reviewFeelsList = reviewEntity.getLikedUserList();
            }
            else {
                userFeelsList = userEntity.getDislikedReviewList();
                reviewFeelsList = reviewEntity.getDislikedUserList();
            }
//            다대다 실패후 주석
//            Iterator<ReviewEntity> feelsIterator = feelsList.iterator();

            boolean foundEquals = false;
//            while(feelsIterator.hasNext()) {
//                if (feelsIterator.next().equals(reviewEntity)) {
//                    foundEquals = true;
//                    feelsIterator.remove();
//                    break;
//                }
//            }

            for(String id : reviewFeelsList) {
                if(id.equals(userId)) {
                    reviewFeelsList.remove(id);
                    userFeelsList.remove(id);
                    break;
                }
            }

            if(!foundEquals) {
                userFeelsList.add(userId);
                reviewFeelsList.add(userId);
            }
            reviewRepository.save(reviewEntity);
            userRepository.save(userEntity);
        }
        catch(Exception e) {
            return ResponseDto.databaseError();
        }
        return PatchReviewResponseDto.success();
    }
}
