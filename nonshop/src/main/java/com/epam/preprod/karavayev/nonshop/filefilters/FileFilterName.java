package com.epam.preprod.karavayev.nonshop.filefilters;

import java.io.File;

public class FileFilterName implements FileFilter {

    private FileFilter next;
    private String name;

    public FileFilterName(String name, FileFilter next) {
        this.name = name;
        this.next = next;
    }

    @Override
    public boolean handleRequest(File file) {
        if (file.getName().startsWith(name)) {
            return next == null || next.handleRequest(file);
        }
        return false;
    }
}
