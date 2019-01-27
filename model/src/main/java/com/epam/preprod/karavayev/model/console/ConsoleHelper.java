package com.epam.preprod.karavayev.model.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    public void promptMessage(String s) {
        System.out.println(s);
    }

    public String takeInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println(e);
        }
        return input;
    }

    public int takeInteger() {
        return Integer.parseInt(takeInput());
    }
}
