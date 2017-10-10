package com.testDao;

import com.client.Department;
import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import com.dao.ProductDao;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("orderDaoTestImpl")
public class OrderDaoTestImpl implements OrderDaoTest {

    @Resource(name = "productDaoTestImpl")
    private ProductDaoTest productDaoTest;

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    public Order createOrder(String productName, int price, Department department, String status, int quantity) {
        Product product  = productDaoTest.createProduct(productName, price, department);
        product.setId(productDao.addProduct(product));
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        order.setStatus(status);
        return order;
    }
}
