package com.epam.preprod.karavayev.web.filter;

import com.epam.preprod.karavayev.constant.HttpHeader;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class PreventCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader(HttpHeader.PRAGMA, "no-cache");
        resp.setHeader(HttpHeader.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        resp.addHeader(HttpHeader.E_TAG, UUID.randomUUID().toString());
        resp.setDateHeader(HttpHeader.EXPIRES, 0);
        resp.addDateHeader(HttpHeader.LAST_MODIFIED, System.currentTimeMillis());
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}
