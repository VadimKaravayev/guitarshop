package com.epam.preprod.karavayev.web.filter;

import com.epam.preprod.karavayev.constant.HttpHeader;
import com.epam.preprod.karavayev.web.filter.gzip.GzipServletResponseWrapper;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (acceptsGZipEncoding(req)) {
            GzipServletResponseWrapper gzipServletResponseWrapper = new GzipServletResponseWrapper(resp);
            gzipServletResponseWrapper.setHeader(HttpHeader.CONTENT_ENCODING, "gzip");
            chain.doFilter(req, gzipServletResponseWrapper);
            gzipServletResponseWrapper.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest req) {
        String acceptEncodingHeader = req.getHeader(HttpHeader.ACCEPT_ENCODING);
        String contentTypeHeader = req.getHeader(HttpHeader.ACCEPT);
        return Objects.nonNull(acceptEncodingHeader)
               && Objects.nonNull(contentTypeHeader)
               && acceptEncodingHeader.contains("gzip")
               && contentTypeHeader.contains("text/");
    }

    @Override
    public void destroy() {

    }
}
