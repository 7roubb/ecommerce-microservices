package com.osama.product_service.products;

import com.osama.product_service.common.ValidLocalizedEntries;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
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
@Builder
public class ProductRequestDto {

    private String id;

    @NotBlank(message = "{product.name.required}")
    @Size(min = 3, max = 100, message = "{name.size.message}")
    private String name;

    @NotBlank(message = "{product.description.required}")
    @Size(min = 3, max = 100, message = "{name.description.message}")
    private String description;

    @NotNull(message = "{price.is.required}")
    @Positive(message = "{price.must.positive}")
    @DecimalMin(value = "0.01", message = "{price.min.size}")
    private double price;

    @NotNull(message = "{category.is.required}")
    private Category category;

    @NotEmpty(message = "{tag.message.empty}")
    private List<String> tags;

    @NotNull(message = "{quantity.is.required}")
    @Min(value = 0, message = "{quantity.must.positive}")
    @Max(value = 10000, message = "{stock.exceed.message}")
    private int quantityInStock;

    private boolean available;

    @ValidLocalizedEntries
    private Map<String, String> localizedNames;

    @ValidLocalizedEntries
    private Map<String, String> localizedDescriptions;

    @NotEmpty(message = "{image.urls.required}")
    private List<@URL(message = "{invalid.url.format}") String> imageUrls;

    @DecimalMin(value = "0.0", message = "{discount.cannot.be.negative}")
    @DecimalMax(value = "100.0", message = "{discount.cannot.exceed}")
    private Double discountPercentage;

    private Boolean onSale;

    @FutureOrPresent(message = "{sale.start.message.error}")
    private Instant saleStart;

    private Instant saleEnd;

    @AssertTrue(message = "{sale.end.message.error}")
    private boolean isValidSalePeriod() {
        if (onSale != null && onSale) {
            return saleStart != null &&
                    saleEnd != null &&
                    saleEnd.isAfter(saleStart);
        }
        return true;
    }

    @AssertTrue(message = "{discount.requires.active.sale}")
    private boolean isValidDiscount() {
        if (discountPercentage != null && discountPercentage > 0) {
            return onSale != null && onSale;
        }
        return true;
    }
}
