package com.dao;

import com.client.Department;
import java.util.List;

public interface DepartmentDao {

    int add(String name);

    Department getById(int id);

    List<Department> getAll();

}
