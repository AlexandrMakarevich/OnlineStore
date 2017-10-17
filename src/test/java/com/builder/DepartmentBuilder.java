package com.builder;

import com.client.Department;

public class DepartmentBuilder {

    private Department department;

    public DepartmentBuilder() {
        init();
    }

    public void init() {
        department = new Department();
        department.setName("Department1");
    }

    public DepartmentBuilder withDepartmentName(String departmentName) {
        department.setName(departmentName);
        return this;
    }

    public DepartmentBuilder withId(int id) {
        department.setId(id);
        return this;
    }

    public Department build() {
        return department;
    }
}
