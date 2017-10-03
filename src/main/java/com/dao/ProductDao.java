package com.dao;

import com.client.Product;
import java.util.List;

public interface ProductDao {

    int addProduct(Product product);

    List<Product> getAllProducts();

    Product getById(int id);
}
