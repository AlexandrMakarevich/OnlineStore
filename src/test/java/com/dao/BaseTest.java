package com.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-store-application.xml")
@Transactional
public abstract  class BaseTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void cleanTable(String tableName) {
        String query = "delete from " + tableName;
        namedParameterJdbcTemplate.getJdbcOperations().update(query);
    }

    public void cleanAllTable() {
        cleanTable("order_order_item_map");
        cleanTable("order_item");
        cleanTable("`order`");
        cleanTable("product_department_map");
        cleanTable("product");
        cleanTable("department");
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
}
