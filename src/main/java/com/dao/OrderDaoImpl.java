package com.dao;

import com.client.Order;
import com.client.OrderItem;
import com.client.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("orderDaoImpl")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    public void addOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int countProductsInOrder = order.getOrderItems().size();
        if (countProductsInOrder == 0) {
            throw new IllegalArgumentException("Order is not initialized");
        }
        String query = "insert into orders (product_id, quantity, status) value(:p_product_id, :p_quantity, :p_status)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("p_product_id", order.getOrderItems().get(0).getProduct().getId());
        mapSqlParameterSource.addValue("p_quantity", order.getOrderItems().get(0).getQuantity());
        mapSqlParameterSource.addValue("p_status", Order.DEFAULT_STATUS);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        int idOfLastInsertOrder = keyHolder.getKey().intValue();
        if (countProductsInOrder == 1) {
            return;
        }
        for (int i = 1; i > countProductsInOrder - 1; i++) {
            String sql = "insert into orders (id, product_id, quantity, status) " +
                    "value(:p_product_id, :p_quantity, :p_status) where id = :p_id";
            MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource();
            mapSqlParameterSource1.addValue("p_id", idOfLastInsertOrder);
            mapSqlParameterSource1.addValue("p_product_id", order.getOrderItems().get(i).getProduct().getId());
            mapSqlParameterSource1.addValue("p_quantity", order.getOrderItems().get(i).getQuantity());
            mapSqlParameterSource1.addValue("p_status", Order.DEFAULT_STATUS);
            namedParameterJdbcTemplate.update(sql, mapSqlParameterSource1);
        }
    }

    public List<Order> getAllOrders() {
        String query = "select * from orders";
        return namedParameterJdbcTemplate.query(query, new ResultSetExtractor<List<Order>>() {
            public List<Order> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Order> orders = new ArrayList<Order>();
                Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
                while (resultSet.next()) {
                    if (orderMap.containsKey(resultSet.getInt("id"))) {
                        Order order = orderMap.get(resultSet.getInt("id"));
                        List<OrderItem> orderItems = order.getOrderItems();
                        OrderItem orderItem = new OrderItem();
                        orderItem.setProduct(productDao.getById(resultSet.getInt("product_id")));
                        orderItem.setQuantity(resultSet.getInt("quantity"));
                        orderItems.add(orderItem);
                        continue;
                    }
                    Order order = createOrder(resultSet);
                    orderMap.put(order.getId(), order);
                }
                for (Map.Entry<Integer, Order> orderEntry : orderMap.entrySet()) {
                    orders.add(orderEntry.getValue());
                }
                return orders;
            }
        });
    }

    public Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem orderItem = new OrderItem();
        Product product = productDao.getById(resultSet.getInt("product_id"));
        orderItem.setProduct(product);
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        order.setStatus(resultSet.getString("status"));
        return order;
    }

    public Order getOrderById(int orderId) {

        return null;
    }
}
