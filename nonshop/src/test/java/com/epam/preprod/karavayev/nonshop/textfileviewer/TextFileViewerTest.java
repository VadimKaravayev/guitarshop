package com.epam.preprod.karavayev.nonshop.textfileviewer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TextFileViewerTest {

    private TextFileViewer textFileViewer;
    private String[] args;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private void writeToFile(File file, String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws IOException {
        File file = folder.newFile();
        args = new String[] {"Η Java είναι μια αντικειμενοστρεφής γλώσσα", "Јава је објектно-оријентисани програмски језик", "Java（ジャバ）は、狭義ではプログラミング言語Javaを指す"};
        writeToFile(file, args[0]);
        writeToFile(file, args[1]);
        writeToFile(file, args[2]);
        textFileViewer = new TextFileViewer(file);
    }

    @Test
    public void shouldReadLinesFromFile() {
        int i = 0;
        for (String s: textFileViewer) {
            assertEquals(s, args[i++]);
        }

        i = 0;
        for (String s: textFileViewer) {
            assertEquals(s, args[i++]);
        }
    }
}