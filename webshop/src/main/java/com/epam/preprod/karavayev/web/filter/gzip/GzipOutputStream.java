package com.epam.preprod.karavayev.web.filter.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GzipOutputStream extends ServletOutputStream {

    private GZIPOutputStream outputStream;


    public GzipOutputStream(OutputStream outputStream) throws IOException {
        this.outputStream = new GZIPOutputStream(outputStream);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }
}
