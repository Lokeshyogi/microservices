package com.erm.client;


import com.erm.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable(value = "id") Long id);
    @GetMapping
    List<Product> getAllProducts();
}

