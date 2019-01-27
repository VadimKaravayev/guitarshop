package com.epam.preprod.karavayev.shop.task9.handler.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.factory.HttpRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.factory.RequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HttpGetItemImplTest {

    private Stock stock;
    private static final String httpRequest = "GET /shop/item?get_info=5 HTTP/1.1";
    private RequestHandlerFactory factory;
    private StringInstrument stringInstrument;

    @Before
    public void setUp()  {
        stock = mock(Stock.class);
        Map<String, RequestHandler> httpHandlers = new HashMap<>();
        httpHandlers.put("/shop/count", new HttpCountImpl(stock));
        httpHandlers.put("/shop/item", new HttpGetItemImpl(stock));
        factory = new HttpRequestHandlerFactory(httpHandlers);
        stringInstrument = new Guitar(3, "Taylor", new BigDecimal(1200), GuitarType.ELECTRIC);
    }

    @Test
    public void shouldReturnItemByIdInJsonFormat() {
        RequestHandler handler = factory.createHandler(httpRequest);
        when(stock.getProductById(5)).thenReturn(stringInstrument);
        String actualResult = handler.handle(httpRequest);
        String expectedResult = "{name:Taylor, price:1200}";
        assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void shouldReturnErrorMessageIfNoProduct() {
        RequestHandler handler = factory.createHandler(httpRequest);
        when(stock.getProductById(5)).thenThrow(new IllegalArgumentException());
        String actualResult = handler.handle(httpRequest);
        String expectedResult = "Resource not found";
        assertTrue(actualResult.contains(expectedResult));
    }
}