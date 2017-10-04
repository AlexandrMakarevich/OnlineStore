package com.dao;

import com.client.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.constant.Constant.DEPARTMENT_ID;
import static com.constant.Constant.DEPARTMENT_NAME;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "queries")
    private Map<String, String> queries;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void add(String name) {
        String query = queries.get("addDepartment");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", name);
        int changeColumn = namedParameterJdbcTemplate.update(query, sqlParameterSource);
        if (changeColumn == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
    }

    public Department getById(int id) {
        String query = queries.get("departmentGetById");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_id", id);
        return namedParameterJdbcTemplate.queryForObject(query, sqlParameterSource, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt(DEPARTMENT_ID));
                department.setName(resultSet.getString(DEPARTMENT_NAME));
                return department;
            }
        });
    }

    public List<Department> getAll() {
        String query = queries.get("getAllDepartment");
        List<Department> departments = namedParameterJdbcTemplate.query(query, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt(DEPARTMENT_ID));
                department.setName(resultSet.getString(DEPARTMENT_NAME));
                return department;
            }
        });
        if (departments.isEmpty()) {
            throw new IllegalArgumentException("You don't have no one department");
        }
        return departments;
    }
}
