package com.epam.preprod.karavayev.db.dao;

import com.epam.preprod.karavayev.entity.Order;

public interface OrderDao {

    boolean create(Order order);
}
