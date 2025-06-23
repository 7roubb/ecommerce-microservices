package com.osama.product_service.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductResponseDto getProductByID(String id) {
        return null;
    }

    @Override
    public ProductRequestDto saveProduct(Product product) {
        return null;
    }

    @Override
    public ProductRequestDto updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }
}
