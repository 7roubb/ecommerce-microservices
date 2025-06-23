package com.osama.product_service.products;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private double price;

    private Category category;
    private List<String> tags;

    private int quantityInStock;
    private boolean available;

    private double averageRating;
    private int numberOfReviews;

    private Map<String, String> localizedNames;
    private Map<String, String> localizedDescriptions;

    private List<String> imageUrls;

    private double discountPercentage;
    private boolean onSale;
    private Instant saleStart;
    private Instant saleEnd;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private boolean deleted;
}
