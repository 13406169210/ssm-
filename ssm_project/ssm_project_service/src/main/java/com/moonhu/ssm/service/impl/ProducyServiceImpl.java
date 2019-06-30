package com.moonhu.ssm.service.impl;

import com.moonhu.ssm.dao.ProductDao;
import com.moonhu.ssm.domain.Product;
import com.moonhu.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * product 业务逻辑层实现类
 */
@Service
public class ProducyServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 添加产品
     * @param product 产品对象
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
