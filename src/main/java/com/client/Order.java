package com.client;

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
}
