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
import java.util.List;

public class TestOrderDaoImpl extends BaseTest {

    @Resource(name = "orderDaoImpl")
    private OrderDao orderDao;

    @Resource(name = "productPersistentBuilder")
    private ProductPersistentBuilder productPersistentBuilder;

    private OrderItemBuilder orderItemBuilder;
    private OrderBuilder orderBuilder;

    @Before
    public void init() {
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

    @Test
    public void testGetAllPendingOrder() {
        List<Order> orders = orderDao.getAllPendingOrder();
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    @Test
    public void getOrderByStatusAndProductName() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setOrderStatus("Pending");
        searchCriteria.setProductName("Product2");
        searchCriteria.setProductQuantity(333);
        List<Order> order = orderDao.getOrderByStatusAndProductName(searchCriteria);
        System.out.println(order);
    }

    @Test
    public void testCountOrder() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setOrderStatus("Pending");
        searchCriteria.setProductName("Product2");
        searchCriteria.setProductQuantity(333);
        Integer count = orderDao.selectCountOrder(searchCriteria);
        System.out.println(count);
    }
}
