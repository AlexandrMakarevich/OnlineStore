package com.dao;

import com.client.Department;
import java.util.List;

public interface DepartmentDao {

    int add(Department department);

    Department getById(int id);

    List<Department> getAll();

}
