package com.client;

import com.google.common.base.Objects;

public class Product {

    private int id;
    private String name;
    private int price;
    private Department department;

    public Product(int id, String name, int price, Department department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                Objects.equal(name, product.name) &&
                Objects.equal(department, product.department);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, price, department);
    }
}
