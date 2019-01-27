package com.epam.preprod.karavayev.nonshop.filefilters;

import java.io.File;

public interface FileFilter {

    boolean handleRequest(File file);
}
