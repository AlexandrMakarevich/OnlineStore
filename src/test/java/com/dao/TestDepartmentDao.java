package com.dao;

import com.client.Department;
import com.testDao.DepartmentDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;

public class TestDepartmentDao extends BaseTest {

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @Resource(name = "departmentDaoTestImpl")
    private DepartmentDaoTest departmentDaoTest;

    @Before
    public void init() {
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddDepartment() {
      String departmentName = "Fishing";
      Department department = departmentDaoTest.createDepartment(departmentName);
      departmentDao.add(department);
      int departmentId = getIdByNameDepartment(departmentName);
      Department actualDepartment = departmentDao.getById(departmentId);
      Assert.assertEquals("Actual result must be expected", departmentName, actualDepartment.getName());
    }

    @Test
    public void testGetAllDepartments() {
        int expectedCountDepartment = 3;
        Department  department1 = departmentDaoTest.createDepartment("Fishing");
        Department department2 = departmentDaoTest.createDepartment("Appliances");
        Department department3 = departmentDaoTest.createDepartment("Household chemicals");
        departmentDao.add(department1);
        departmentDao.add(department2);
        departmentDao.add(department3);
        List<Department> departments = departmentDao.getAll();
        int actualCountDepartment = departments.size();
        Assert.assertEquals("Actual result must be expected", actualCountDepartment, expectedCountDepartment);
    }
}
