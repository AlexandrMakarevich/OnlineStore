package com.dao;

import com.client.Department;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.constant.Constant.DEPARTMENT_ID;
import static com.constant.Constant.DEPARTMENT_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-store-application.xml")
public abstract  class BaseMethodsTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void cleanTable(String tableName) {
        String query = "delete from " + tableName;
        namedParameterJdbcTemplate.getJdbcOperations().update(query);
    }

    public int getIdByNameDepartment(String departmentName) {
        String query = "select id department_id, department_name from department where department_name = :p_name";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", departmentName);
        Department department = namedParameterJdbcTemplate.queryForObject(query, sqlParameterSource, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt(DEPARTMENT_ID));
                department.setName(resultSet.getString(DEPARTMENT_NAME));
                return department;
            }
        });
        return department.getId();
    }

    public int createDepartment(String depName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into department(department_name) value(:p_name)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", depName);
        int rowChanged = namedParameterJdbcTemplate.update(query, sqlParameterSource, keyHolder);
        if (rowChanged == 0) {
            throw new IllegalStateException("No column was changed");
        }
        return keyHolder.getKey().intValue();
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
}
