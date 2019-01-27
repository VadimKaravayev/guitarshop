package com.epam.preprod.karavayev.shop.task9.factory;

import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;

public interface RequestHandlerFactory {

    RequestHandler createHandler(String stringRequest);

    String getErrorMessage();
}
