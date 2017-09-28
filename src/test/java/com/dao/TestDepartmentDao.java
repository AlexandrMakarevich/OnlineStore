package com.dao;

import com.client.Department;
import com.google.common.base.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestDepartmentDao extends BaseMethodsTest{

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @Before
    public void init() {
        cleanTable("departments");
    }

    @Test
    public void testAddDepartment() {
      String departmentName = "Fishing";
      departmentDao.add(departmentName);
      int departmentId = getIdByName(departmentName);
      Optional<Department> departmentOptional = departmentDao.getById(departmentId);
      Assert.assertEquals("Actual result must be expected", departmentName, departmentOptional.get().getName());
    }

    @Test
    public void testGetAllDepartments() {
        int expectedCountDepartment = 3;
        String department1 = "Fishing";
        String department2 = "Appliances";
        String department3 = "Household chemicals";
        departmentDao.add(department1);
        departmentDao.add(department2);
        departmentDao.add(department3);
        List<Department> departments = departmentDao.getAll();
        int actualCountDepartment = departments.size();
        Assert.assertEquals("Actual result must be expected", actualCountDepartment, expectedCountDepartment);
    }

    public int getIdByName (String departmentName) {
        String query = "select * from departments where name = :p_name";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("p_name", departmentName);
        Department department = getNamedParameterJdbcTemplate().queryForObject(query, sqlParameterSource, new RowMapper<Department>() {
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                return department;
            }
        });
        return department.getId();
    }
}
