package com.epam.preprod.karavayev.shop.task9.handler;

import com.epam.preprod.karavayev.model.productstock.Stock;

public abstract class HttpRequestHandler extends RequestHandler {

    public static final String HTTP_OK_HEADER = "HTTP/1.1 200 OK\r\n" +
                                                "Content-Type: text/html; charset=UTF-8\r\n" +
                                                "Connection: close\r\n\r\n\r\n";

    private static final String ERROR_MESSAGE = "HTTP/1.1 404 Not Found\r\n" +
                                                "Content-Type: text/html; charset=UTF-8\r\n" +
                                                "Connection: close\r\n\r\n\r\n" +
                                                "Resource not found\r\n";

    public HttpRequestHandler(Stock stock) {
        super(stock);
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
