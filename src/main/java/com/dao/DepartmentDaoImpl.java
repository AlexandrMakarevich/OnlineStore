package com.dao;

import com.client.Department;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void add(String name) {
        String query = "insert into departments(name) value(:p_name)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", name);
        int changeColumn = namedParameterJdbcTemplate.update(query, sqlParameterSource);
        if (changeColumn == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
    }

    public Optional<Department> getById(int id) {
        String query = "select * from departments where id = :p_id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_id", id);
        List<Department> departments = namedParameterJdbcTemplate.query(query, sqlParameterSource, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                return department;
            }
        });
        if (departments.isEmpty()) {
            return Optional.absent();
        }
        return Optional.of(departments.get(0));
    }

    public List<Department> getAll() {
        String query = "select * from departments";
        List<Department> departments = namedParameterJdbcTemplate.query(query, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                return department;
            }
        });
        if (departments.isEmpty()) {
            throw new IllegalArgumentException("You don't have no one department");
        }
        return departments;
    }
}
