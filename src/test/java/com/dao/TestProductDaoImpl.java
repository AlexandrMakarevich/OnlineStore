package com.dao;

import com.client.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestProductDaoImpl extends BaseMethodsTest {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @Before
    public void init(){
        cleanTable("products");
        cleanTable("departments");
    }

    @Test
    public void testAddProduct() {
        String productName = "TV";
        int departmentId = insertDepartment("Department1");
        Product product = new Product();
        product.setName(productName);
        product.setPrice(300);
        product.setDepartmentId(departmentId);
        productDao.addProduct(product);
        Product actualProduct = productDao.getById(productName);
        Assert.assertEquals("Actual result must be expected", actualProduct.getName(), productName);
    }

    @Test
    public void testGetAllProducts() {
        int expectedProductSum = 2;
        int departmentId1 = insertDepartment("Department1");
        int departmentId2 = insertDepartment("Department2");
        Product product = new Product();
        product.setName("TV");
        product.setPrice(300);
        product.setDepartmentId(departmentId1);
        productDao.addProduct(product);
        Product product2 = new Product();
        product2.setName("Telephone");
        product2.setPrice(500);
        product2.setDepartmentId(departmentId2);
        productDao.addProduct(product2);
        int actualProductSum = productDao.getAllProducts().size();
        Assert.assertEquals("Actual result must be expected", actualProductSum, expectedProductSum);
    }
}
