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
    public static Product toProduct(ProductRequestDto requestDto) {
        return Optional.ofNullable(requestDto)
                .map(r -> Product.builder()
                        .name(r.getName())
                        .description(r.getDescription())
                        .price(r.getPrice())
                        .category(r.getCategory())
                        .tags(r.getTags())
                        .quantityInStock(r.getQuantityInStock())
                        .available(r.isAvailable())
                        .averageRating(0.0) // default value for new products
                        .numberOfReviews(0) // default value for new products
                        .localizedNames(r.getLocalizedNames())
                        .localizedDescriptions(r.getLocalizedDescriptions())
                        .imageUrls(r.getImageUrls())
                        .discountPercentage(
                                r.getDiscountPercentage() != null ? r.getDiscountPercentage() : 0.0
                        )
                        .onSale(
                                r.getOnSale() != null ? r.getOnSale() : false
                        )
                        .saleStart(r.getSaleStart())
                        .saleEnd(r.getSaleEnd())
                        .deleted(false)
                        .build())
                .orElse(null);
    }
}
