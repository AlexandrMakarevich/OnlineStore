package com.client;

import com.date.JsonDateDeserializer;
import com.date.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_order_item_map",
            joinColumns =  @JoinColumn(name = "order_id") ,
            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Column(name = "status")
    @NotNull
    private String status;

    @Column(name = "date_of_order")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd yyyy HH:mm:ss 'GMT'ZZZ (z)", timezone="GMT+3")
    private Instant dateOfOrder = Instant.now();

    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Instant getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Instant dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", status='" + status + '\'' +
                ", dateOfOrder=" + dateOfOrder +
                '}';
    }
}
