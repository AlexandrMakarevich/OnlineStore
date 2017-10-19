package com.client;

import com.google.common.base.Objects;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    @NotNull
    private String name;

    @Column(name = "price")
    @NotNull
    private int price;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_department_map",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "department_id"))
    @NotNull
    private List<Department> departments = new ArrayList<Department>();

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equal(id, product.id) &&
                Objects.equal(name, product.name) &&
                Objects.equal(departments, product.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, price, departments);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", departments=" + departments +
                '}';
    }
}
