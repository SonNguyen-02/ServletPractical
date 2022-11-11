package com.mct.practical.practical3.domain.use_cases;

import com.mct.practical.practical3.domain.use_cases.product.*;

public class ProductUseCases {

    private final CreateProduct createProduct;
    private final UpdateProduct updateProduct;
    private final RemoveProduct removeProduct;
    private final GetProducts getProducts;
    private final GetProduct getProduct;

    public ProductUseCases(CreateProduct createProduct,
                           UpdateProduct updateProduct,
                           RemoveProduct removeProduct,
                           GetProducts getProducts, GetProduct getProduct) {
        this.createProduct = createProduct;
        this.updateProduct = updateProduct;
        this.removeProduct = removeProduct;
        this.getProducts = getProducts;
        this.getProduct = getProduct;
    }

    public CreateProduct getCreateProduct() {
        return createProduct;
    }

    public UpdateProduct getUpdateProduct() {
        return updateProduct;
    }

    public RemoveProduct getRemoveProduct() {
        return removeProduct;
    }

    public GetProducts getGetProducts() {
        return getProducts;
    }

    public GetProduct getGetProduct() {
        return getProduct;
    }
}
