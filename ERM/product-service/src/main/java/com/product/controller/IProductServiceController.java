package com.product.controller;

import com.product.model.Product;

import java.util.List;

public interface IProductServiceController {
    public Product updateProduct(Long id, Product product);
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public void deleteProduct(Long id);
}
