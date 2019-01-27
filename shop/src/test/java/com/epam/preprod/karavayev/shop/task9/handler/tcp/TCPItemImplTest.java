package com.epam.preprod.karavayev.shop.task9.handler.tcp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.factory.TCPRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TCPItemImplTest {

    private Stock stock;
    private TCPRequestHandlerFactory factory;

    @Before
    public void setUp() {

        stock = mock(Stock.class);
        Map<String, RequestHandler> tcpHandlers = new HashMap<>();
        tcpHandlers.put("get count",  new TCPCountImpl(stock));
        tcpHandlers.put("get item=", new TCPItemImpl(stock));
        factory = new TCPRequestHandlerFactory(tcpHandlers);
    }

    @Test
    public void shouldReturnProductInStringById() {
        String stringRequest = "get item=3";
        when(stock.getProductById(3))
                .thenReturn(new Guitar(3, "Taylor", new BigDecimal(1200), GuitarType.ELECTRIC));
        RequestHandler handler = factory.createHandler(stringRequest);
        String expectedResult = "Taylor | 1200";
        String actualResult = handler.handle(stringRequest);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnErrorMessageWhenProductNotInStock() {
        String stringRequest = "get item=100";
        when(stock.getProductById(100))
                .thenThrow(new IllegalArgumentException());
        RequestHandler handler = factory.createHandler(stringRequest);
        String expectedResult = "resource not found";
        String actualResult = handler.handle(stringRequest);
        assertEquals(expectedResult, actualResult);
    }
}