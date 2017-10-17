package com.controller;

import com.builder.DepartmentPersistentBuilder;
import com.builder.ProductBuilder;
import com.client.Product;
import com.dao.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestProductRestController extends BaseTest {

    @Resource(name = "productDaoRestController")
    private RestProductDaoController restProductDaoController;

    @Resource(name = "departmentPersistentBuilder")
    private DepartmentPersistentBuilder departmentPersistentBuilder;
    private ProductBuilder productBuilder;

    @Before
    public void init() {
        cleanAllTable();
        productBuilder = new ProductBuilder();
    }

    @Test
    public void testAddProduct() {
        Product product = productBuilder.withDepartment(departmentPersistentBuilder.buildAndAddDepartment())
                .build();
        restProductDaoController.addProduct(product);
        Product actualProduct = restProductDaoController.getProductById(product.getId());
        Assert.assertEquals("Actual result must be expected", actualProduct, product);
    }
}
