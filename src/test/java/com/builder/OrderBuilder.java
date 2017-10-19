package com.builder;

import com.client.Order;
import com.client.OrderItem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private Order order;
    private List<OrderItem> orderItems;

    public OrderBuilder() {
        init();
    }

    public void init() {
        orderItems = new ArrayList<>();
        order = new Order();
        order.setDateOfOrder(Instant.now());
        order.setStatus("Pending");
        order.setOrderItems(orderItems);
    }

    public OrderBuilder withStatus(String status) {
        order.setStatus(status);
        return this;
    }

    public OrderBuilder withListOrderItem(List<OrderItem> listOrderItem) {
        order.getOrderItems().addAll(listOrderItem);
        return this;
    }

    public Order build() {
        return order;
    }
}
