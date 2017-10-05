package com.dao;

import com.client.Order;
import java.util.List;

public interface OrderDao {

    int addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);
}
