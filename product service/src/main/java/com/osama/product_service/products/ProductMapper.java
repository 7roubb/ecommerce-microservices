package com.osama.product_service.products;

import java.util.Optional;

public class ProductMapper {
    public static ProductResponseDto toResponseDto(Product product) {
        return Optional.ofNullable(product)
                .map(p -> ProductResponseDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .category(p.getCategory())
                        .tags(p.getTags())
                        .quantityInStock(p.getQuantityInStock())
                        .available(p.isAvailable())
                        .averageRating(p.getAverageRating())
                        .numberOfReviews(p.getNumberOfReviews())
                        .localizedNames(p.getLocalizedNames())
                        .localizedDescriptions(p.getLocalizedDescriptions())
                        .imageUrls(p.getImageUrls())
                        .discountPercentage(p.getDiscountPercentage())
                        .onSale(p.isOnSale())
                        .saleEnd(p.getSaleEnd())
                        .saleStart(p.getSaleStart())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build()
                ).orElse(null);

    }
}
