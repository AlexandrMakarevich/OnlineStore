package com.testDao;

import com.client.Department;
import org.springframework.stereotype.Repository;

@Repository("departmentDaoTestImpl")
public class DepartmentDaoTestImpl implements DepartmentDaoTest {

    public Department createDepartment(String departmentName) {
        Department department = new Department();
        department.setName(departmentName);
        return department;
    }
}
