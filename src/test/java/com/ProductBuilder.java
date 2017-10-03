package com;

import com.client.Department;
import com.client.Product;

public class ProductBuilder {

    public Product createProduct(String productName, int price, Department department) {
        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setDepartment(department);
        return product;
    }
}
