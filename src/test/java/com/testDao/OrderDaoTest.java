package com.testDao;

import com.client.Order;

public interface OrderDaoTest {

    Order createOrder(String productName, int price, String departmentName, String status, int quantity);
}
