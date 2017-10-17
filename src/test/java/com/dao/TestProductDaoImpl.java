package com.dao;

import com.builder.DepartmentPersistentBuilder;
import com.builder.ProductBuilder;
import com.client.Department;
import com.client.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.annotation.Resource;

public class TestProductDaoImpl extends BaseTest {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @Resource(name = "departmentPersistentBuilder")
    private DepartmentPersistentBuilder departmentPersistentBuilder;

    private ProductBuilder productBuilder;

    @Before
    public void init(){
        cleanAllTable();
        productBuilder = new ProductBuilder();
    }

    @Test
    @Rollback(false)
    public void testAddProduct() {
        String productName = "TV";
        Department department = departmentPersistentBuilder.buildAndAddDepartment();
        Product product = productBuilder.withName(productName)
                .withPrice(388)
                .withDepartment(department)
                .build();
        int actualId = productDao.addProduct(product);
        Product actualProduct = productDao.getById(actualId);
        Assert.assertEquals("Actual result must be expected", actualProduct, product);
    }
}
