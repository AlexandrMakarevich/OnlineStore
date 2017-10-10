package com.testDao;

import com.client.Department;
import com.client.Product;

public interface ProductDaoTest {

    Product createProduct(String productName, int price, Department department);
}
