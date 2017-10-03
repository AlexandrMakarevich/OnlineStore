package com.dao;

import com.builder.ProductBuilderFromResultSet;
import com.client.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "productBuilderFromResultSet")
    private ProductBuilderFromResultSet productBuilderFromResultSet;

    public int addProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into product(product_name, price, department_id) values(:p_name, :p_price, :p_departmentId)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("p_name", product.getName());
        mapSqlParameterSource.addValue("p_price", product.getPrice());
        mapSqlParameterSource.addValue("p_departmentId", product.getDepartment().getId());
        int changeColumn = namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        if (changeColumn == 0) {
            throw new IllegalArgumentException("No column was changed");
        }
        return keyHolder.getKey().intValue();
    }

    public List<Product> getAllProducts() {
        String query = "select product.id product_id, product.product_name," +
                " product.price, product.department_id, department.name " +
                "from product inner join department on product.department_id=department.id";
        List<Product> products = namedParameterJdbcTemplate.query(query, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
               return productBuilderFromResultSet.buildFromResultSet(resultSet);
            }
        });
        if (products.isEmpty()) {
            throw new IllegalStateException("You don't have any product");
        }
        return products;
    }

    public Product getById(int id) {
        String query = "select product.id product_id, product.product_name," +
                " product.price, product.department_id, department.name " +
                "from product inner join department on product.department_id=department.id " +
                "where product.id = :p_id;";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_id", id);
        Product product = namedParameterJdbcTemplate.queryForObject(query, sqlParameterSource, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                return productBuilderFromResultSet.buildFromResultSet(resultSet);
            }
        });
        return product;
    }
}
