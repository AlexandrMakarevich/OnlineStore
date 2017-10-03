package com.dao;

import com.ProductBuilder;
import com.client.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestProductDaoImpl extends BaseMethodsTest {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;
    private ProductBuilder productBuilder;

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @Before
    public void init(){
        cleanTable("product");
        cleanTable("department");
        productBuilder = new ProductBuilder();
    }

    @Test
    public void testAddProduct() {
        String productName = "TV";
        int departmentId = createDepartment("Department1");
        int actualId = productDao.addProduct(productBuilder.createProduct(productName, 300, departmentDao.getById(departmentId).get()));
        Product actualProduct = productDao.getById(actualId);
        Assert.assertEquals("Actual result must be expected", actualProduct.getName(), productName);
    }

    @Test
    public void testGetAllProducts() {
        int expectedProductSum = 2;
        int departmentId1 = createDepartment("Department1");
        int departmentId2 = createDepartment("Department2");
        productDao.addProduct(productBuilder.createProduct("TV", 300, departmentDao.getById(departmentId1).get()));
        productDao.addProduct(productBuilder.createProduct("Telephone", 500, departmentDao.getById(departmentId2).get()));
        int actualProductSum = productDao.getAllProducts().size();
        Assert.assertEquals("Actual result must be expected", actualProductSum, expectedProductSum);
    }
}
