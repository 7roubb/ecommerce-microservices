package com.osama.product_service.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponseDto {
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
    private Instant createdAt;
    private Instant updatedAt;
}
