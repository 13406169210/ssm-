package com.moonhu.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.moonhu.ssm.dao.OrdersDao;
import com.moonhu.ssm.domain.Order;
import com.moonhu.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Order> findAll(Integer page, Integer size) {
        // 开启分页
        PageHelper.startPage(page, size);
        List<Order> orderList = ordersDao.findAll();
        return orderList;
    }

    /**
     * 通过order的id查询详细数据
     *
     * @param id
     * @return
     */
    @Override
    public Order findById(String id) {
        return ordersDao.findById(id);

    }
}
