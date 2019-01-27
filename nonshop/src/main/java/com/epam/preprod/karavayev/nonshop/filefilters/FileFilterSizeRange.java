package com.epam.preprod.karavayev.nonshop.filefilters;

import java.io.File;

public class FileFilterSizeRange implements FileFilter {

    private FileFilter next;
    private long size1;
    private long size2;

    public FileFilterSizeRange(long size1, long size2, FileFilter next) {
        this.next = next;
        this.size1 = size1;
        this.size2 = size2;
    }

    @Override
    public boolean handleRequest(File file) {
        if (file.length() >= size1 && file.length() <= size2) {
            return next == null || next.handleRequest(file);
        }
        return false;
    }
}
