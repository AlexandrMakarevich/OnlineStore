package com.controller;

import com.client.Order;
import com.dao.OrderDao;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController("restOrderDaoController")
public class RestOrderDaoController {

    @Resource(name = "orderDaoImpl")
    private OrderDao orderDao;

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Order getById(@PathVariable int id) {
       return orderDao.getOrderById(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void addOrder(@RequestBody Order order) {
        orderDao.addOrder(order);
    }
}
