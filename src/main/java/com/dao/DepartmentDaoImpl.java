package com.dao;

import com.client.Department;
import org.springframework.stereotype.Repository;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {

    public int add(Department department) {
        getSession().save(department);
        return department.getId();
    }

    public Department getById(int id) {
        return getSession().load(Department.class, id);
    }
}
