package com.epam.preprod.karavayev.nonshop.filefilters;

import java.io.File;

public class FileFilterExtension implements FileFilter {

    private FileFilter next;
    private String extension;

    public FileFilterExtension(String extension, FileFilter next) {
        this.next = next;
        this.extension = extension;
    }

    @Override
    public boolean handleRequest(File file) {
        if (file.getName().toLowerCase().endsWith(extension)) {
            return next == null || next.handleRequest(file);
        }
        return false;
    }
}
