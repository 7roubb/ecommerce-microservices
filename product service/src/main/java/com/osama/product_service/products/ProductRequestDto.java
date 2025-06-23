package com.osama.product_service.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Valid
public class ProductRequestDto {

    @NotBlank(message = "product.name.required")
    @Size(min = 3, max = 100, message = "name.size.message")
    private String name;

    @NotBlank(message = "product.description.required")
    @Size(min = 3, max = 100, message = "name.description.message")
    private String description;

    @NotNull(message = "price.is.required")
    @Positive(message = "price.must.positive")
    @DecimalMin(value = "0.01",message = "price.min.size")
    private double price;

    @NotNull(message = "category.is.required")
    private Category category;

    @NotEmpty(message = "tag.message.empty")
    private List<String> tags;

    @NotNull(message = "quantity.is.required")
    @Min(value = 0, message = "quantity.must.positive")
    @Max(value = 10000, message = "stock.exceed.message")
    private int quantityInStock;

    private boolean available;


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
