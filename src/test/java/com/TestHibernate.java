package com;

import com.client.Department;
import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import com.dao.BaseTest;
import com.dao.DepartmentDao;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

public class TestHibernate extends BaseTest {

    @Resource(name = "departmentDaoHibernate")
    private DepartmentDao departmentDao;

    @Resource(name = "productDaoHibernate")
    private ProductDao productDao;

    @Resource(name = "orderDaoHibernate")
    private OrderDao orderDao;


    @Test
    @Rollback(true)
    public void test() {
        Department department = new Department();
        department.setName("Department35");
        int depId = departmentDao.add(department);
        department.setId(depId);

        Product product = new Product();
        product.setDepartment(department);
        product.setPrice(333);
        product.setName("Prod35");
        int prodId = productDao.addProduct(product);
        product.setId(prodId);

        Order order = new Order();
        order.setStatus("Pending");
        order.setOrderItems(ImmutableList.of(new OrderItem(product, 10)));
        orderDao.addOrder(order);
    }
}
