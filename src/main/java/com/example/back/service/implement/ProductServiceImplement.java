package com.example.back.service.implement;

import com.example.back.dto.response.product.SaveProductResponseDto;
import com.example.back.entity.ProductEntity;
import com.example.back.repository.ProductRepository;
import com.example.back.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {

    private final String CLIENT_ID = "yHnbTL8e5WJbijzhlTJC";
    private final String CLIENT_SECRET = "zALwPNV7B6";

    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<? super SaveProductResponseDto> saveProductsFromApi(String keyword) {
        String url = "https://openapi.naver.com/v1/search/shop.json?query=" + keyword + "&display=20";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, NaverResponse.class);
        NaverResponse naverResponse = responseEntity.getBody();
        System.out.println(responseEntity.getBody());
        if (naverResponse != null && naverResponse.getItems() != null) {
            List<ProductEntity> productEntities = naverResponse.getItems().stream().map(item -> {
                ProductEntity product = new ProductEntity();
                product.setProductId(item.getProductId());
                product.setTitle(removeHtmlTags(item.getTitle()));
                product.setLink(item.getLink());
                product.setImage(item.getImage());
                product.setLowPrice(item.getLprice());
                product.setCount(item.getCount());
                product.setCategory1(item.getCategory1());
                product.setCategory2(item.getCategory2());

                Optional<ProductEntity> existingProduct = productRepository.findByTitle(product.getTitle());
                if (existingProduct.isPresent()) {
                    return null;
                } else {
                    return product;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());

            productRepository.saveAll(productEntities);

            return SaveProductResponseDto.success(productEntities);
        }
        return SaveProductResponseDto.fail();
    }


    private static class NaverResponse {
        private List<Item> items;
        public List<Item> getItems() {
            return items;
        }
        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    @Getter
    @Setter
    private static class Item {
        private Long productId;
        private String title;
        private String link;
        private String image;
        private String lprice;
        private int count;
        private String category1;
        private String category2;
    }

    private String removeHtmlTags(String html) {
        return html.replaceAll("\\<.*?\\>", "");
    }
}
