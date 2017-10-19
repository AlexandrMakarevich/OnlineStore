package com.dao;

import com.client.Order;
import java.util.List;

public interface OrderDao {

    int addOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllPendingOrder();

    List<Order> getOrderByStatusAndProductName(SearchCriteria searchCriteria);

    Integer selectCountOrder(SearchCriteria searchCriteria);
}
