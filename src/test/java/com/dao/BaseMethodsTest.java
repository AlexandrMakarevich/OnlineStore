package com.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-store-application.xml")
public class BaseMethodsTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void cleanTable(String tableName) {
        String query = "delete from " + tableName;
        namedParameterJdbcTemplate.getJdbcOperations().update(query);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
}
