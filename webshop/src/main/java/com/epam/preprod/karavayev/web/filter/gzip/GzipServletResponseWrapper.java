package com.epam.preprod.karavayev.web.filter.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipServletResponseWrapper extends HttpServletResponseWrapper {

    private GzipOutputStream gzip;
    private PrintWriter writer;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @throws IllegalArgumentException if the response is null
     */
    public GzipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        if (Objects.nonNull(writer)) {
            writer.close();
        }
        if (Objects.nonNull(gzip)) {
            gzip.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (Objects.isNull(writer) && Objects.isNull(gzip)) {
            return;
        }
        if (Objects.nonNull(writer)) {
            writer.flush();
        } else {
            gzip.flush();
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (Objects.nonNull(writer)) {
            throw new IllegalStateException("Writer already used");
        }
        if (Objects.isNull(gzip)) {
            gzip = new GzipOutputStream(getResponse().getOutputStream());
        }
        return gzip;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (Objects.nonNull(gzip)) {
            throw new IllegalStateException("Output stream already used");
        }
        if (Objects.isNull(writer)) {
            GzipOutputStream gzipOutputStream = new GzipOutputStream(getResponse().getOutputStream());
            writer = new PrintWriter(new OutputStreamWriter(gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return writer;
    }
}
