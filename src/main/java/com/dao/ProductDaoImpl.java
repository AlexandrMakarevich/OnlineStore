package com.dao;

import com.client.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDao implements ProductDao {

    public int addProduct(Product product) {
        getSession().save(product);
        return product.getId();
    }

    public Product getById(int id) {
        return getSession().load(Product.class, id);
    }
}
