package com;

import com.client.Product;

public class ProductBuilder {

    public Product createProduct(String productName, int price, int departmentId) {
        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setDepartmentId(departmentId);
        return product;
    }
}
