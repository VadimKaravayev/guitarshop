package com.epam.preprod.karavayev.model.productstock;

import java.util.Date;
import java.util.TreeMap;

public class OrderDatabase {

    private TreeMap<Date, Order> orders;

    public OrderDatabase() {
        orders = new TreeMap<>();
    }

    public void addToOrderDatabase(Date date, Order order) {
        order.setOrderDate(date);
        orders.put(date, order);
    }

    public TreeMap<Date, Order> getOrders() {
        return orders;
    }
}
