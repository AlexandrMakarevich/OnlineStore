package com.dao;

import com.client.Product;

public interface ProductDao {

    int addProduct(Product product);

    Product getById(int id);
}
