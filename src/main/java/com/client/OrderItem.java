package com.client;

import com.google.common.base.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    public OrderItem() {

    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity &&
                Objects.equal(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product, quantity);
    }
}
