package com.dao;

import com.client.Order;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDao implements OrderDao {

    public int addOrder(Order order) {
        getSession().save(order);
        return order.getId();
    }

    public Order getOrderById(int orderId) {
        return getSession().load(Order.class, orderId);
    }

    public List<Order> getAllPendingOrder() {
        Criteria criteria = getSession().createCriteria(Order.class)
                .add(Restrictions.eq("status", "Pending"));
        return criteria.list();
    }

    public List<Order> getOrderByStatusAndProductName(SearchCriteria searchCriteria) {
        Criteria criteria = getSession().createCriteria(Order.class, "order");
        if (!searchCriteria.isOrderStatusNull()) {
            criteria.add(Restrictions.eq("order.status", searchCriteria.getOrderStatus()));
        }
        if (!searchCriteria.isProductNameNull() || !searchCriteria.isProductQuantityNull()) {
            criteria.createAlias("orderItems", "item");
        }
        if (!searchCriteria.isProductNameNull()) {
            criteria.createAlias("item.product", "p");
            criteria.add(Restrictions.eq("p.name", searchCriteria.getProductName()));
        }
        if(!searchCriteria.isProductQuantityNull()) {
            criteria.add(Restrictions.eq("item.quantity", searchCriteria.getProductQuantity()));
        }
        return criteria.list();
    }

    public Integer selectCountOrder(SearchCriteria searchCriteria) {
        Criteria criteria = getSession().createCriteria(Order.class, "order");
        if (!searchCriteria.isOrderStatusNull()) {
            criteria.add(Restrictions.eq("order.status", searchCriteria.getOrderStatus()));
        }
        if (!searchCriteria.isProductNameNull() || !searchCriteria.isProductQuantityNull()) {
            criteria.createAlias("orderItems", "item");
        }
        if (!searchCriteria.isProductNameNull()) {
            criteria.createAlias("item.product", "p");
            criteria.add(Restrictions.eq("p.name", searchCriteria.getProductName()));
        }
        if(!searchCriteria.isProductQuantityNull()) {
            criteria.add(Restrictions.eq("item.quantity", searchCriteria.getProductQuantity()));
        }
        return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
