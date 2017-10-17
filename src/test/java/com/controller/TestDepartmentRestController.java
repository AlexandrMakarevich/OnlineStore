package com.controller;

import com.builder.DepartmentBuilder;
import com.client.Department;
import com.dao.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestDepartmentRestController extends BaseTest {

    @Resource(name = "restDepartmentController")
    private RestDepartmentController restDepartmentController;

    private DepartmentBuilder departmentBuilder;

    @Before
    public void init() {
        cleanAllTable();
        departmentBuilder = new DepartmentBuilder();
    }

    @Test
    public void testRestAddDepartment() {
        Department department = departmentBuilder.build();
        restDepartmentController.addDepartment(department);
        Department actualDepartment = restDepartmentController.getIdById(department.getId());
        Assert.assertEquals("Actual result must be expected", actualDepartment, department);
    }
}
