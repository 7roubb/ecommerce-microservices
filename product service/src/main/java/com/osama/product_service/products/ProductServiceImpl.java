package com.osama.product_service.products;

import com.osama.product_service.exceptions.CustomExceptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public Boolean saveProduct(ProductRequestDto productDto) {
        // Check for duplicate product name
        productRepository.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productDto.getName()))
                .findAny()
                .ifPresent(p -> {
                    throw new CustomExceptions.ProductAlreadyExistsException(productDto.getName());
                });

        // Map DTO to entity
        Product product = ProductMapper.toProduct(productDto);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        product.setDeleted(false);

        productRepository.save(product);

        log.info("Product saved successfully: {}", product.getName());
        return true;
    }


    @Override
    @Transactional
    public Boolean updateProduct(ProductRequestDto product) {
        log.debug("Attempting to update product with ID: {}", product.getId());
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
                    existingProduct.setOnSale(product.getOnSale());
                    Optional.ofNullable(product.getSaleStart()).ifPresent(existingProduct::setSaleStart);
                    Optional.ofNullable(product.getSaleEnd()).ifPresent(existingProduct::setSaleEnd);
                    existingProduct.setUpdatedAt(Instant.now());
                    productRepository.save(existingProduct);
                    return true;
                })
                .orElseThrow(() -> {
                    log.error("Cannot update. Product not found with ID: {}", product.getId());
                    return new CustomExceptions.ProductNotFoundException(product.getId());
                });    }

    @Override
    @Transactional
    public Boolean deleteProduct(String id) {
        log.debug("Attempting to delete product with ID: {}", id);
        return productRepository.findById(id)
                .map(product -> {
                    product.setDeleted(true);
                    product.setUpdatedAt(Instant.now());
                    productRepository.save(product);
                    log.warn("Product soft-deleted: {}", product.getName());
                    return true;
                })
                .orElseThrow(() -> {
                    log.error("Cannot delete. Product not found with ID: {}", id);
                    return new CustomExceptions.ProductNotFoundException(id);
                });
    }
}
