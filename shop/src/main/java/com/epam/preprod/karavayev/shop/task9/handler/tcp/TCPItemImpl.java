package com.epam.preprod.karavayev.shop.task9.handler.tcp;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Stock;
import com.epam.preprod.karavayev.shop.task9.handler.TcpRequestHandler;

public class TCPItemImpl extends TcpRequestHandler {

    public TCPItemImpl(Stock stock) {
        super(stock);
    }

    @Override
    public String handle(String stringRequest) {
        int id = Integer.parseInt(stringRequest.replaceAll("[^\\d]", ""));
        StringInstrument stringInstrument = null;
        try {
            stringInstrument = stock.getProductById(id);
        } catch (Exception e) {
            return  getErrorMessage();
        }
        return stringInstrument.getName() + " | " + stringInstrument.getPrice().toString();
    }

}
