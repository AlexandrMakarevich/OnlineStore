package com.builder;

import com.client.Department;
import com.dao.DepartmentDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("departmentPersistentBuilder")
public class DepartmentPersistentBuilder {

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    private DepartmentBuilder departmentBuilder = new DepartmentBuilder();

    public Department buildAndAddDepartment() {
        Department department = departmentBuilder.build();
        departmentDao.add(department);
        return department;
    }
}
