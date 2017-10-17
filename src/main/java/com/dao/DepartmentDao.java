package com.dao;

import com.client.Department;

public interface DepartmentDao {

    int add(Department department);

    Department getById(int id);

}
