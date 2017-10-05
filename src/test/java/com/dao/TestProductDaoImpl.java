package com.dao;

import com.client.Product;
import com.testDao.ProductDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestProductDaoImpl extends BaseMethodsTest {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @Resource(name = "productDaoTestImpl")
    private ProductDaoTest productDaoTest;

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @Before
    public void init(){
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddProduct() {
        String productName = "TV";
        String departmentName = "Department1";
        Product product = productDaoTest.createProduct(productName, 300, departmentName);
        int actualId = productDao.addProduct(product);
        product.setId(actualId);
        Product actualProduct = productDao.getById(actualId);
        Assert.assertEquals("Actual result must be expected", actualProduct, product);
    }

    @Test
    public void testGetAllProducts() {
        int expectedProductSum = 2;
        String departmentName = "Department1";
        String departmentName2 = "Department2";
        Product product1 = productDaoTest.createProduct("TV", 400, departmentName);
        Product product2 = productDaoTest.createProduct("Telephone", 600, departmentName2);
        productDao.addProduct(product1);
        productDao.addProduct(product2);
        int actualProductSum = productDao.getAllProducts().size();
        Assert.assertEquals("Actual result must be expected", actualProductSum, expectedProductSum);
    }
}
