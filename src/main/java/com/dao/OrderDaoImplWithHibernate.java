package com.dao;

import com.client.Order;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("orderDaoHibernate")
public class OrderDaoImplWithHibernate extends BaseDao implements OrderDao {

    public int addOrder(Order order) {
        getSession().save(order);
        return order.getId();
    }

    public List<Order> getAllOrders() {
        return null;
    }

    public Order getOrderById(int orderId) {
        return null;
    }
}
