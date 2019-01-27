package com.epam.preprod.karavayev.shop.task9.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws IOException {

        String localhost = InetAddress.getByName("localhost").getHostAddress();
        int port = 3000;
        try (Socket socket = new Socket(localhost, port);) {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String line = console.readLine();
            printWriter.println(line);
            System.out.println(bufferedReader.readLine());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
