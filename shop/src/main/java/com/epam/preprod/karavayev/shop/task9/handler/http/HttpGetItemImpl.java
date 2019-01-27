package com.epam.preprod.karavayev.shop.task9.handler.http;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.handler.HttpRequestHandler;

public class HttpGetItemImpl extends HttpRequestHandler {

    public HttpGetItemImpl(Stock stock) {
        super(stock);
    }

    @Override
    public String handle(String request) {
        String result = extractKeyValue(request);
        result = result.replaceAll("\\D", "");
        if (!result.isEmpty()) {
            int id = Integer.parseInt(result);

            StringInstrument stringInstrument;
            try {
                stringInstrument = stock.getProductById(id);
            } catch (Exception e) {
                return getErrorMessage();
            }
            return HTTP_OK_HEADER + String.format("{name:%s, price:%s}",
                                                  stringInstrument.getName(),
                                                  stringInstrument.getPrice().toString()) + "\r\n\r\n";
        }
        return getErrorMessage();
    }

    private String extractKeyValue(String request) {
        return request.split("\\?")[1].split("\\s")[0];
    }
}
