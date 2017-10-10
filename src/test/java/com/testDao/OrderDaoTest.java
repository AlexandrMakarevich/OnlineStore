package com.testDao;

import com.client.Department;
import com.client.Order;

public interface OrderDaoTest {

    Order createOrder(String productName, int price, Department department, String status, int quantity);
}
