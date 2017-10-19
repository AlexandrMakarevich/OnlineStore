package com.dao;

import com.builder.DepartmentBuilder;
import com.client.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.List;

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
        Department actualDepartment = departmentDao.getByIdWithCriteria(departmentId);
        Assert.assertEquals("Actual result must be expected", actualDepartment, department);
    }

    @Test
    public void test() {
        List<Department> departments = departmentDao.getAllDepartment();
        System.out.println(departments);
    }
}
