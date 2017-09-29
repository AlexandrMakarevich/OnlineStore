package com.dao;

import com.client.Department;
import com.google.common.base.Optional;
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
}
