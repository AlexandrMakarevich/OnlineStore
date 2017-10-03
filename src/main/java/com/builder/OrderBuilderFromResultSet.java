package com.builder;

import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import static com.constant.Constant.ORDER_ID;
import static com.constant.Constant.QUANTITY;
import static com.constant.Constant.STATUS;

@Repository("orderBuilderFromResultSet")
public class OrderBuilderFromResultSet {

    @Resource(name = "productBuilderFromResultSet")
    private ProductBuilderFromResultSet productBuilder;

    public Order buildOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = productBuilder.buildFromResultSet(resultSet);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(resultSet.getInt(QUANTITY));
        Order order = new Order();
        order.setId(resultSet.getInt(ORDER_ID));
        order.setStatus(resultSet.getString(STATUS));
        order.addOrderItem(orderItem);
        return order;
    }

    public void addInExistingOrder(Order order, Map<Integer, Order> orderMap) throws SQLException {
        Order existingOrder = orderMap.get(order.getId());
        existingOrder.addOrderItems(order.getOrderItems());
    }
}
