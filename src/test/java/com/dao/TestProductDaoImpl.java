package com.dao;

import com.client.Department;
import com.client.Product;
import com.testDao.DepartmentDaoTest;
import com.testDao.ProductDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class TestProductDaoImpl extends BaseTest {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @Resource(name = "productDaoTestImpl")
    private ProductDaoTest productDaoTest;

    @Resource(name = "departmentDaoTestImpl")
    private DepartmentDaoTest departmentDaoTest;

    @Before
    public void init(){
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddProduct() {
        String productName = "TV";
        Department department = departmentDaoTest.createDepartment("Department1");
        Product product = productDaoTest.createProduct(productName, 300, department);
        int actualId = productDao.addProduct(product);
        product.setId(actualId);
        Product actualProduct = productDao.getById(actualId);
        Assert.assertEquals("Actual result must be expected", actualProduct, product);
    }

    @Test
    public void testGetAllProducts() {
        int expectedProductSum = 2;
        Department department1 = departmentDaoTest.createDepartment("Department1");
        Department department2 = departmentDaoTest.createDepartment("Department2");
        Product product1 = productDaoTest.createProduct("TV", 400, department1);
        Product product2 = productDaoTest.createProduct("Telephone", 600, department2);
        productDao.addProduct(product1);
        productDao.addProduct(product2);
        int actualProductSum = productDao.getAllProducts().size();
        Assert.assertEquals("Actual result must be expected", actualProductSum, expectedProductSum);
    }
}
