package com.controller;

import com.client.Department;
import com.dao.BaseMethodsTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;

public class TestDepartmentRestController extends BaseMethodsTest {

    @Resource(name = "restDepartmentController")
    private RestDepartmentController restDepartmentController;

    @Before
    public void init() {
        cleanTable("product");
        cleanTable("department");
    }

    @Test
    public void testAddDepartment() {
        String departmentName = "Electro devices";
        restDepartmentController.addDepartment(departmentName);
        int idAddedDepartment = getIdByNameDepartment(departmentName);
        Department department = restDepartmentController.getIdById(idAddedDepartment);
        Assert.assertEquals("DepartmentName must be the same as department.getName()", department.getName(), departmentName);
    }

    @Test
    public void testGetAllDepartments() {
        int expectedCountDepartment = 3;
        String department1 = "Fishing";
        String department2 = "Appliances";
        String department3 = "Household chemicals";
        restDepartmentController.addDepartment(department1);
        restDepartmentController.addDepartment(department2);
        restDepartmentController.addDepartment(department3);
        List<Department> departments = restDepartmentController.getAllDepartment();
        int actualCountDepartment = departments.size();
        Assert.assertEquals("Actual result must be expected", actualCountDepartment, expectedCountDepartment);
    }
}
