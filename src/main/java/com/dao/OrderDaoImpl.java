package com.dao;

import com.client.Order;
import org.springframework.stereotype.Repository;

@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDao implements OrderDao {

    public int addOrder(Order order) {
        getSession().save(order);
        return order.getId();
    }

    public Order getOrderById(int orderId) {
        return getSession().load(Order.class, orderId);
    }
}
