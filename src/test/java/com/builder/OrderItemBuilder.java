package com.builder;

import com.client.OrderItem;
import com.client.Product;

public class OrderItemBuilder {

    private OrderItem orderItem;

    public OrderItemBuilder() {
        init();
    }

    public void init() {
        orderItem = new OrderItem();
        orderItem.setQuantity(333);
        orderItem.setProduct(new Product());
    }

    public OrderItemBuilder withProduct(Product product) {
        orderItem.setProduct(product);
        return this;
    }

    public OrderItemBuilder withQuantity(Integer quantity) {
        orderItem.setQuantity(quantity);
        return this;
    }

    public OrderItem build() {
        return orderItem;
    }
}
