package com.product.service;


import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    Product updateProduct(Long id, Product productDetails);
    Product getProductById(Long id);
    List<Product> getAllProducts() ;
    void deleteProduct(Long id);
}
