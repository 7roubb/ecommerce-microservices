package com.osama.product_service.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product getProductByID(String id);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);


}
