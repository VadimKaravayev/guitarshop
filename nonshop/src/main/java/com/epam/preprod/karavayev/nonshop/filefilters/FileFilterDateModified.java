package com.epam.preprod.karavayev.nonshop.filefilters;

import java.io.File;

public class FileFilterDateModified implements FileFilter {

    private FileFilter next;
    private long date1;
    private long date2;

    public FileFilterDateModified(long date1, long date2, FileFilter next) {
        this.next = next;
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public boolean handleRequest(File file) {
        if (file.lastModified() >= date1 && file.lastModified() <= date2) {
            return next == null || next.handleRequest(file);
        }
        return false;
    }
}
