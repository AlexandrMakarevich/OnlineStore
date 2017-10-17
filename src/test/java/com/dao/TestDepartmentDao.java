package com.dao;

import com.builder.DepartmentBuilder;
import com.client.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.annotation.Resource;

public class TestDepartmentDao extends BaseTest {

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    private DepartmentBuilder departmentBuilder;

    @Before
    public void init() {
      cleanAllTable();
      departmentBuilder = new DepartmentBuilder();
    }

    @Rollback(true)
    @Test
    public void testAddDepartment() {
        String departmentName = "Fishing";
        Department department = departmentBuilder.withDepartmentName(departmentName)
                                                 .build();
        int departmentId = departmentDao.add(department);
        Department actualDepartment = departmentDao.getById(departmentId);
        Assert.assertEquals("Actual result must be expected", actualDepartment, department);
    }
}
