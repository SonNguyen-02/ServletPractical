package com.mct.practical.practical3.domain.use_cases.product;

import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.repository.ProductRepository;

public class RemoveProduct {

    private final ProductRepository productRepository;

    public RemoveProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean invoke(int id) {
        return productRepository.removeProduct(id);
    }

    public boolean invoke(Product product) {
        return productRepository.removeProduct(product);
    }

}
