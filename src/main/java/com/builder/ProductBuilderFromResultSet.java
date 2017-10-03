package com.builder;

import com.client.Department;
import com.client.Product;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.constant.Constant.PRICE;
import static com.constant.Constant.PRODUCT_ID;
import static com.constant.Constant.PRODUCT_NAME;

@Repository("productBuilderFromResultSet")
public class ProductBuilderFromResultSet {

    @Resource(name = "departmentBuilderFromResultSet")
    private DepartmentBuilderFromResultSet departmentBuilder;

    public Product buildFromResultSet(ResultSet resultSet ) throws SQLException {
        Department department = departmentBuilder.buildFromResultSet(resultSet);
        Product product = new Product();
        product.setId(resultSet.getInt(PRODUCT_ID));
        product.setName(resultSet.getString(PRODUCT_NAME));
        product.setPrice(resultSet.getInt(PRICE));
        product.setDepartment(department);
        return product;
    }
}
