package com.epam.preprod.karavayev.shop.task9.handler;

import com.epam.preprod.karavayev.model.productstock.Stock;

public abstract class RequestHandler {

    protected Stock stock;

    public RequestHandler(Stock stock) {
        this.stock = stock;
    }

    public abstract String handle(String request);

    public abstract String getErrorMessage();

}

