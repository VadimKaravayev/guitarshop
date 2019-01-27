package com.epam.preprod.karavayev.shop.task9.factory;

import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TCPRequestHandlerFactory implements RequestHandlerFactory {

    private Map<String, RequestHandler> handlers;
    private String errorMessage = "Unknown command";

    public TCPRequestHandlerFactory(Map<String, RequestHandler> handlers) {
        this.handlers = handlers;
    }

    public RequestHandler createHandler(String stringRequest) {
        return handlers.get(parseInput(stringRequest));
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    private String parseInput(String input) {

        String regex = "(get\\s\\w+=?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String command = "";
        while (matcher.find()) {
            command = matcher.group(1);
        }
        return command;
    }

}
