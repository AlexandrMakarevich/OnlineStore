package com.dao;

import com.client.Department;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("departmentDaoHibernate")
public class DepartmentDaoImplWithHibernate extends BaseDao implements DepartmentDao {

    public int add(Department department) {
        getSession().save(department);
        return department.getId();
    }

    public Department getById(int id) {
        return null;
    }

    public List<Department> getAll() {
        return null;
    }
}
