package com.builder;

import com.client.Department;
import com.client.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    private Product product;
    private List<Department> departments;

    public ProductBuilder() {
        init();
    }

    public void init() {
        departments = new ArrayList<>();
        product = new Product();
        product.setName("Product2");
        product.setPrice(300);
        product.setDepartments(departments);
    }

    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder withId(int id) {
        product.setId(id);
        return this;
    }

    public ProductBuilder withPrice(int price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder withDepartment(Department department) {
        List<Department> departments = product.getDepartments();
        departments.add(department);
        return this;
    }

    public Product build() {
        return product;
    }
}
