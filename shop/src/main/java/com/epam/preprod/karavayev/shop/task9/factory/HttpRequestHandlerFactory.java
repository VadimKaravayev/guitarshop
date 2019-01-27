package com.epam.preprod.karavayev.shop.task9.factory;

import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestHandlerFactory implements RequestHandlerFactory {

    private Map<String, RequestHandler> handlers;
    public static final String ERROR_MESSAGE = "HTTP/1.1 404 Not Found\r\n" +
                                        "Content-Type: text/html; charset=UTF-8\r\n" +
                                        "Connection: close\r\n\r\n\r\n" +
                                        "Resource not found\r\n";

    public HttpRequestHandlerFactory(Map<String, RequestHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public RequestHandler createHandler(String stringRequest) {
        return handlers.get(extractUrl(stringRequest));
    }

    private String extractUrl(String string) {
        String regex = "(GET\\s)(\\/\\w+\\/\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String url = "";
        while (matcher.find()) {
            url = matcher.group(2);
        }
        return url;
    }

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }


}
