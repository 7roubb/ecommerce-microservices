package com.osama.product_service.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Page<Product> findByDeletedFalse(Pageable pageable);

    Optional<Product> findByIdAndDeletedFalse(String id);

    Optional<Product> findByNameIgnoreCaseAndDeletedFalse(String name);

    Optional<Product> findById(String id);
}