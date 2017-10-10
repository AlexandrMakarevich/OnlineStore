package com.controller;

import com.client.Department;
import com.dao.BaseTest;
import com.testDao.DepartmentDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;

public class TestDepartmentRestController extends BaseTest {

    @Resource(name = "restDepartmentController")
    private RestDepartmentController restDepartmentController;

    @Resource(name = "departmentDaoTestImpl")
    private DepartmentDaoTest departmentDaoTest;

    @Before
    public void init() {
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddDepartment() {
        String departmentName = "Electro devices";
        Department department = departmentDaoTest.createDepartment(departmentName);
        restDepartmentController.addDepartment(department);
        int idAddedDepartment = getIdByNameDepartment(departmentName);
        Department actualDepartment = restDepartmentController.getIdById(idAddedDepartment);
        Assert.assertEquals("DepartmentName must be the same as department.getName()", actualDepartment.getName(), departmentName);
    }

    @Test
    public void testGetAllDepartments() {
        int expectedCountDepartment = 3;
        Department department1 = departmentDaoTest.createDepartment("Fishing");
        Department department2 = departmentDaoTest.createDepartment("Appliances");
        Department department3 = departmentDaoTest.createDepartment("Household chemicals");
        restDepartmentController.addDepartment(department1);
        restDepartmentController.addDepartment(department2);
        restDepartmentController.addDepartment(department3);
        List<Department> departments = restDepartmentController.getAllDepartment();
        int actualCountDepartment = departments.size();
        Assert.assertEquals("Actual result must be expected", actualCountDepartment, expectedCountDepartment);
    }
}
