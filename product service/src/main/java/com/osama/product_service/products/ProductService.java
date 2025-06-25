package com.osama.product_service.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> findAll(Pageable pageable);
    ProductResponseDto getProductByID(String id);
    Boolean saveProduct(ProductRequestDto product);
    Boolean updateProduct(ProductRequestDto product);
    Boolean deleteProduct(String id);
}
