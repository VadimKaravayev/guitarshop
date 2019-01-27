package com.epam.preprod.karavayev.model.productstock;

import java.util.Date;
import java.util.Map;

public class Order {

    // id and amount
    private Map<Integer, Integer> order;
    private Date orderDate;

    public Order(Map<Integer, Integer> order) {
        this.order = order;
    }

    public Map<Integer, Integer> getOrder() {
        return order;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order=" + order +
                ", orderDate=" + orderDate +
                '}';
    }
}
