package com.controller;

import com.builder.OrderBuilder;
import com.builder.OrderItemBuilder;
import com.builder.ProductPersistentBuilder;
import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import com.dao.BaseTest;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.annotation.Resource;

public class TestOrderDaoController extends BaseTest {

    @Resource(name = "restOrderDaoController")
    private RestOrderDaoController restOrderDaoController;

    @Resource(name = "productPersistentBuilder")
    private ProductPersistentBuilder productPersistentBuilder;

    private OrderBuilder orderBuilder;
    private OrderItemBuilder orderItemBuilder;

    @Before
    public void init() {
        cleanAllTable();
        orderBuilder = new OrderBuilder();
        orderItemBuilder = new OrderItemBuilder();
    }

    @Test
    @Rollback(false)
    public void testRestAddOrder() {
        Product product = productPersistentBuilder.buildAndAddProduct();
        OrderItem orderItem = orderItemBuilder.withProduct(product).build();
        Order order = orderBuilder.withListOrderItem(ImmutableList.of(orderItem)).build();
        restOrderDaoController.addOrder(order);
        Order actualOrder = restOrderDaoController.getById(order.getId());
        Assert.assertEquals("Actual result must be expected", actualOrder, order);
    }
}
