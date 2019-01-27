package com.epam.preprod.karavayev.shop.task9.handler.tcp;

import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.handler.TcpRequestHandler;

public class TCPCountImpl extends TcpRequestHandler {

    public TCPCountImpl(Stock stock) {
        super(stock);
    }

    @Override
    public String handle(String request) {
        int count = stock.getProductCount();
        return count + " products available";
    }

}
