package com.client;

import com.google.common.base.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_order_item_map",
            joinColumns =  @JoinColumn(name = "order_id") ,
            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Column(name = "status")
    @NotNull
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
