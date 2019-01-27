package com.epam.preprod.karavayev.nonshop.textfileviewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.Scanner;

public class TextFileViewer implements Iterable<String> {
    private Scanner scanner;
    private File file;

    TextFileViewer(File file) {
        this.file = file;
    }

    @Override
    public Iterator<String> iterator() {

        try {
            scanner = new Scanner(file, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };
    }
}
