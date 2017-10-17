package com.dao;

import com.client.Order;

public interface OrderDao {

    int addOrder(Order order);

    Order getOrderById(int orderId);
}
