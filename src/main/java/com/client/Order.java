package com.client;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Integer id;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private String status;
    public static final String DEFAULT_STATUS = "Pending";

    public void addOrderItems(List<OrderItem> orderItemList) {
        orderItems.addAll(orderItemList);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equal(id, order.id) &&
                Objects.equal(orderItems, order.orderItems) &&
                Objects.equal(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, orderItems, status);
    }
}
