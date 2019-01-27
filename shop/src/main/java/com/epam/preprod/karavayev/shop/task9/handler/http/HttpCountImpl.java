package com.epam.preprod.karavayev.shop.task9.handler.http;

import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.handler.HttpRequestHandler;

public class HttpCountImpl extends HttpRequestHandler {

    public HttpCountImpl(Stock stock) {
        super(stock);
    }

    @Override
    public String handle(String request) {
        int count = stock.getProductCount();
        return HTTP_OK_HEADER + "{count:" + count + "}" + "\r\n";
    }
}
