package com.builder;

import com.client.Department;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.constant.Constant.DEPARTMENT_ID;
import static com.constant.Constant.NAME;

@Repository("departmentBuilderFromResultSet")
public class DepartmentBuilderFromResultSet {

    public Department buildFromResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt(DEPARTMENT_ID));
        department.setName(resultSet.getString(NAME));
        return department;
    }
}
