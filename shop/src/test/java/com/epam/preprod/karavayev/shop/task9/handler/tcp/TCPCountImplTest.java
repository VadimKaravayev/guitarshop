package com.epam.preprod.karavayev.shop.task9.handler.tcp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.factory.TCPRequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TCPCountImplTest {

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
    public void shouldReturnCountOfProductsInStock() {
        when(stock.getProductCount()).thenReturn(100);
        TCPCountImpl tcpCount = (TCPCountImpl) factory.createHandler("get count");
        String actualResult = tcpCount.handle("get count");
        String expectedResult = "100 products available";
        assertEquals(expectedResult, actualResult);
    }
}