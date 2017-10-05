package com.dao;

import com.client.Order;
import com.testDao.OrderDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestOrderDaoImpl extends BaseMethodsTest {

    @Resource(name = "orderDaoImpl")
    private OrderDao orderDao;

    @Resource(name = "orderDaoTestImpl")
    private OrderDaoTest orderDaoTest;

    @Before
    public void init() {
        cleanTable("order_item");
        cleanTable("`order`");
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddOrder() {
        String departmentName = "Department1";
        String productName = "Microwave";
        String status = "Pending";
        int quantity = 1;
        int price = 777;
        Order expectedOrder = orderDaoTest.createOrder(productName, price, departmentName, status, quantity);
        Integer orderId = orderDao.addOrder(expectedOrder);
        expectedOrder.setId(orderId);
        Order actualOrder = orderDao.getOrderById(orderId);
        Assert.assertEquals("Actual result must be expected", actualOrder.getId(), expectedOrder.getId());
        Assert.assertEquals("Actual result must be expected", actualOrder, expectedOrder);
    }
}
