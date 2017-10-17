package com.dao;

import com.builder.OrderBuilder;
import com.builder.OrderItemBuilder;
import com.builder.ProductPersistentBuilder;
import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.annotation.Resource;

public class TestOrderDaoImpl extends BaseTest {

    @Resource(name = "orderDaoImpl")
    private OrderDao orderDao;

    @Resource(name = "productPersistentBuilder")
    private ProductPersistentBuilder productPersistentBuilder;

    private OrderItemBuilder orderItemBuilder;
    private OrderBuilder orderBuilder;

    @Before
    public void init() {
        cleanAllTable();
        orderItemBuilder = new OrderItemBuilder();
        orderBuilder = new OrderBuilder();
    }

    @Test
    @Rollback(false)
    public void testAddOrder() {
        Product product = productPersistentBuilder.buildAndAddProduct();
        OrderItem orderItem = orderItemBuilder.withProduct(product).build();
        Order order = orderBuilder.withListOrderItem(ImmutableList.of(orderItem))
                .build();
        int actualProductId = orderDao.addOrder(order);
        Order actualOrder = orderDao.getOrderById(actualProductId);
        Assert.assertEquals("Actual result must be expected", actualOrder, order);
    }
}
