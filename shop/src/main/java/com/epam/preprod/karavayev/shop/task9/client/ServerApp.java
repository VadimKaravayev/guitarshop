package com.epam.preprod.karavayev.shop.task9.client;

import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.factory.HttpRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.factory.RequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.factory.TCPRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import com.epam.preprod.karavayev.shop.task9.handler.http.HttpCountImpl;
import com.epam.preprod.karavayev.shop.task9.handler.http.HttpGetItemImpl;
import com.epam.preprod.karavayev.shop.task9.handler.tcp.TCPCountImpl;
import com.epam.preprod.karavayev.shop.task9.handler.tcp.TCPItemImpl;
import com.epam.preprod.karavayev.shop.task9.server.Server;

import java.util.HashMap;
import java.util.Map;

public class ServerApp {

    public static void main(String[] args) {

        Stock stock = new Stock();

        Map<String, RequestHandler> tcpHandlers = new HashMap<>();
        tcpHandlers.put("get count",  new TCPCountImpl(stock));
        tcpHandlers.put("get item=", new TCPItemImpl(stock));
        new ServerApp().runServer(3000, new TCPRequestHandlerFactory(tcpHandlers));

        Map<String, RequestHandler> httpHandlers = new HashMap<>();
        httpHandlers.put("/shop/count", new HttpCountImpl(stock));
        httpHandlers.put("/shop/item", new HttpGetItemImpl(stock));
        new ServerApp().runServer(3001, new HttpRequestHandlerFactory(httpHandlers));

    }

    public void runServer(int port, RequestHandlerFactory factory) {
        new Server(port, factory).start();
    }

}
