package com.epam.preprod.karavayev.shop.task9.handler.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.factory.HttpRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.factory.RequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpCountImplTest {

    private Stock stock;
    private static final String HTTP_REQUEST = "GET /shop/count HTTP/1.1";
    private RequestHandlerFactory factory;

    @Before
    public void setUp() {
        stock = mock(Stock.class);
        Map<String, RequestHandler> httpHandlers = new HashMap<>();
        httpHandlers.put("/shop/count", new HttpCountImpl(stock));
        httpHandlers.put("/shop/item", new HttpGetItemImpl(stock));
        factory = new HttpRequestHandlerFactory(httpHandlers);
    }

    @Test
    public void shouldReturnCountOfProductsInStockInJson() {
        RequestHandler handler = factory.createHandler(HTTP_REQUEST);
        when(stock.getProductCount()).thenReturn(100);
        String expectedResult = "{count:100}";
        assertTrue(handler.handle(HTTP_REQUEST).contains(expectedResult));
    }
}