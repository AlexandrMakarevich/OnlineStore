package com.dao;

import com.builder.OrderBuilderFromResultSet;
import com.client.Order;
import com.client.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.constant.Constant.ORDER_ID;

@Repository("orderDaoImpl")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "queries")
    private Map<String, String> queries;

    @Resource(name = "orderBuilderFromResultSet")
    private OrderBuilderFromResultSet orderBuilder;

    public void addOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = queries.get("addOrder");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("p_status", Order.DEFAULT_STATUS);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        int idOfLastInsertOrder = keyHolder.getKey().intValue();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            String sql = queries.get("addOrderItem");
            MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource();
            mapSqlParameterSource1.addValue("p_id", idOfLastInsertOrder);
            mapSqlParameterSource1.addValue("p_product_id", orderItem.getProduct());
            mapSqlParameterSource1.addValue("p_quantity", orderItem.getQuantity());
            namedParameterJdbcTemplate.update(sql, mapSqlParameterSource1);
        }
    }

    public List<Order> getAllOrders() {
        String query = queries.get("getAllOrder");
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
        return new ArrayList<Order>(orders);
    }

    public Order getOrderById(final int orderId) {
        String query = queries.get("getOrderById");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_id", ORDER_ID);
        return namedParameterJdbcTemplate.query(query, sqlParameterSource, new ResultSetExtractor<Order>() {
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
                while (rs.next()) {
                    Order order = orderBuilder.buildOrderFromResultSet(rs);
                    if (orderMap.isEmpty()) {
                        orderMap.put(order.getId(), order);
                        continue;
                    }
                    orderBuilder.addInExistingOrder(order, orderMap);
                }
                return orderMap.get(orderId);
            }
        });
    }
}
