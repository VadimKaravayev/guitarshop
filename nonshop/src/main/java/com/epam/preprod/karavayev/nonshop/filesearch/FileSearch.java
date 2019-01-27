package com.epam.preprod.karavayev.nonshop.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    private List<File> filesOfDirectory = new ArrayList<>();

    private void search(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File temp: files) {
                if (temp.isDirectory()) {
                    search(temp);
                } else {
                    filesOfDirectory.add(temp);
                }
            }
        }
    }

    public List<File> getFilesOfDirectory(File file) {
        filesOfDirectory.clear();
        search(file);
        return filesOfDirectory;
    }
}
