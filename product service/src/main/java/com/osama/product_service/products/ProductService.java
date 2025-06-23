package com.osama.product_service.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> findAll(Pageable pageable);
    ProductResponseDto getProductByID(String id);
    ProductRequestDto saveProduct(Product product);
    ProductRequestDto updateProduct(Product product);
    void deleteProduct(String id);


}
