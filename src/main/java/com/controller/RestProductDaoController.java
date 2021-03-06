package com.controller;

import com.client.Product;
import com.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("productDaoRestController")
@Transactional
public class RestProductDaoController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") int id) {
        return productDao.getById(id);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productDao.addProduct(product);
    }
}
