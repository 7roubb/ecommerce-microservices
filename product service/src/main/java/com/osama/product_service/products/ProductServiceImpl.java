package com.osama.product_service.products;

import com.osama.product_service.exceptions.CustomExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toResponseDto);    }

    @Override
    public ProductResponseDto getProductByID(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::toResponseDto)
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException(id));
    }

    @Override
    @Transactional
    public Boolean saveProduct(Product product) {
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        product.setDeleted(false);
        productRepository.save(product);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateProduct(Product product) {
        return productRepository.findById(product.getId())
                .map(existingProduct -> {
                    Optional.ofNullable(product.getName()).ifPresent(existingProduct::setName);
                    Optional.ofNullable(product.getDescription()).ifPresent(existingProduct::setDescription);
                    Optional.of(product.getPrice()).ifPresent(existingProduct::setPrice);
                    Optional.ofNullable(product.getCategory()).ifPresent(existingProduct::setCategory);
                    Optional.ofNullable(product.getTags()).ifPresent(existingProduct::setTags);
                    Optional.of(product.getQuantityInStock()).ifPresent(existingProduct::setQuantityInStock);
                    existingProduct.setAvailable(product.isAvailable());
                    Optional.ofNullable(product.getLocalizedNames()).ifPresent(existingProduct::setLocalizedNames);
                    Optional.ofNullable(product.getLocalizedDescriptions()).ifPresent(existingProduct::setLocalizedDescriptions);
                    Optional.ofNullable(product.getImageUrls()).ifPresent(existingProduct::setImageUrls);
                    Optional.of(product.getDiscountPercentage()).ifPresent(existingProduct::setDiscountPercentage);
                    existingProduct.setOnSale(product.isOnSale());
                    Optional.ofNullable(product.getSaleStart()).ifPresent(existingProduct::setSaleStart);
                    Optional.ofNullable(product.getSaleEnd()).ifPresent(existingProduct::setSaleEnd);
                    existingProduct.setUpdatedAt(Instant.now());
                    productRepository.save(existingProduct);
                    return true;
                })
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException(product.getId()));
    }

    @Override
    @Transactional
    public Boolean deleteProduct(String id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setDeleted(true);
                    product.setUpdatedAt(Instant.now());
                    productRepository.save(product);
                    return true;
                })
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException(id));
    }
}
