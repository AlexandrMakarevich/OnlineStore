package com.dao;

import com.client.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestDepartmentDao extends BaseMethodsTest{

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @Before
    public void init() {
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddDepartment() {
      String departmentName = "Fishing";
      departmentDao.add(departmentName);
      int departmentId = getIdByNameDepartment(departmentName);
      Department department = departmentDao.getById(departmentId);
      Assert.assertEquals("Actual result must be expected", departmentName, department.getName());
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
}
