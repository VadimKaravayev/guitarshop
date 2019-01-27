package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.OrderDao;
import com.epam.preprod.karavayev.entity.Order;
import com.epam.preprod.karavayev.service.OrderService;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDao orderDao, TransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void create(Order order) {
        transactionManager.manageTransaction(() -> orderDao.create(order), Connection.TRANSACTION_READ_COMMITTED);
    }
}
