package com.dao;

import com.client.Order;
import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);
}
