package com.builder;

import com.client.Department;
import com.client.Product;
import com.dao.ProductDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productPersistentBuilder")
public class ProductPersistentBuilder {

    private ProductBuilder productBuilder = new ProductBuilder();

    @Resource(name = "departmentPersistentBuilder")
    private DepartmentPersistentBuilder departmentPersistentBuilder;

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    public Product buildAndAddProduct() {
        Department department = departmentPersistentBuilder.buildAndAddDepartment();
        Product product = productBuilder.withDepartment(department).build();
        productDao.addProduct(product);
        return product;
    }
}
