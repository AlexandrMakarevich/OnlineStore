package com.dao;

import com.client.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("productDaoHibernate")
public class ProductDaoImplWithHibernate extends BaseDao implements ProductDao {

    public int addProduct(Product product) {
        getSession().save(product);
        return product.getId();
    }

    public List<Product> getAllProducts() {
        return null;
    }

    public Product getById(int id) {
        return null;
    }
}
