package com.dao;

import com.builder.OrderBuilderFromResultSet;
import com.client.Order;
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
import java.util.*;

@Repository("orderDaoImpl")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "orderBuilderFromResultSet")
    private OrderBuilderFromResultSet orderBuilder;

    public void addOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int countProductsInOrder = order.getOrderItems().size();
        if (countProductsInOrder == 0) {
            throw new IllegalArgumentException("Order is not initialized");
        }
        String query = "insert into order (product_id, quantity, status) value(:p_product_id, :p_quantity, :p_status)";
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
            String sql = "insert into order (id, product_id, quantity, status) " +
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
        String query = "select order.id, order.product_id, order.quantity, order.status," +
                " product.product_name, product.price, product.department_id,department.name " +
                "from order inner join product on order.product_id=product.id " +
                "inner join department on product.department_id=department.id " +
                "order by order.id, product.id";
         Collection<Order> orders = namedParameterJdbcTemplate.query(query, new ResultSetExtractor<Collection<Order>>() {
            public Collection<Order> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
                while (resultSet.next()) {
                    Order order = orderBuilder.buildOrderFromResultSet(resultSet);
                    if (orderMap.containsKey(order.getId())) {
                        orderBuilder.addInExistingOrder(order, orderMap);
                        continue;
                    }
                    orderMap.put(order.getId(), order);
                }
                return orderMap.values();
            }
        });
         return  new ArrayList<Order>(orders);
    }

    public Order getOrderById(int orderId) {
//        String query = "select * from orders where id = :p_id";
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_id", orderId);
//        namedParameterJdbcTemplate.query(query, sqlParameterSource, new ResultSetExtractor<Order>() {
//            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
//                return null;
//            }
//        });
        return null;
    }
}
