package com.epam.preprod.karavayev.shop.task9.handler;

import com.epam.preprod.karavayev.model.productstock.Stock;

public abstract class TcpRequestHandler extends RequestHandler {

    public TcpRequestHandler(Stock stock) {
        super(stock);
    }

    @Override
    public String getErrorMessage() {
        return "resource not found";
    }
}
