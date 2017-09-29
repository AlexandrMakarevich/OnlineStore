package com.dao;

import com.client.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void addProduct(Product product) {
        String query = "insert into products(product_name, price, department_id) values(:p_name, :p_price, :p_departmentId)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("p_name", product.getName());
        mapSqlParameterSource.addValue("p_price", product.getPrice());
        mapSqlParameterSource.addValue("p_departmentId", product.getDepartmentId());
        int changeColumn = namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
        if (changeColumn == 0) {
            throw new IllegalArgumentException("No column was changed");
        }
    }

    public List<Product> getAllProducts() {
        String query = "select * from products";
        List<Product> products = namedParameterJdbcTemplate.query(query, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getInt("price"));
                product.setDepartmentId(resultSet.getInt("department_id"));
                return product;
            }
        });
        if (products.isEmpty()) {
            throw new IllegalStateException("You don't have any product");
        }
        return products;
    }

    public Product getByName(String name) {
        String query = "select * from products where product_name = :p_name";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", name);
        Product product = namedParameterJdbcTemplate.queryForObject(query, sqlParameterSource, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getInt("price"));
                product.setDepartmentId(resultSet.getInt("department_id"));
                return product;
            }
        });
        return product;
    }
}
