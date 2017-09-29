package com.controller;

import com.client.Product;
import com.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController("productDaoRestController")
@Transactional
public class RestProductDaoController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @RequestMapping(value = "/product/{name}", method = RequestMethod.GET)
    public Product getProductByName(@PathVariable("name") String name) {
        return productDao.getByName(name);
    }

    @RequestMapping(value = "/allProducts", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        if (products.isEmpty()) {
            throw new IllegalStateException("You don't have any products");
        }
        return products;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productDao.addProduct(product);
    }
}
