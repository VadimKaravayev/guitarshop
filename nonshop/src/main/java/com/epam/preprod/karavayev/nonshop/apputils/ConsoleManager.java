package com.epam.preprod.karavayev.nonshop.apputils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleManager {

    public void promptMessage(String message) {
        System.out.println(message);
    }

    public String takeInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void printList(List<File> results) {
        results.forEach(System.out::println);
        promptMessage(results.size() + " files found\n");
    }
}
