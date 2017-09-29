package com.client;

public class Product {

    private int id;
    private String name;
    private int price;
    private int departmentId;

    public Product(int id, String name, int price, int departmentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.departmentId = departmentId;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
