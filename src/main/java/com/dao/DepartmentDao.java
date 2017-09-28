package com.dao;

import com.client.Department;
import com.google.common.base.Optional;
import java.util.List;

public interface DepartmentDao {

    void add(String name);

    Optional<Department> getById(int id);

    List<Department> getAll();

}
