package com.dao;

import com.client.Product;
import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    List<Product> getAllProducts();

    Product getByName(String name);
}
