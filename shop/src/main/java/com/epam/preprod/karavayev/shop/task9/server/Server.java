package com.epam.preprod.karavayev.shop.task9.server;

import com.epam.preprod.karavayev.shop.task9.factory.RequestHandlerFactory;
import com.epam.preprod.karavayev.shop.task9.handler.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private static final int NUMBER_OF_THREADS = 10;
    private ServerSocket serverSocket;
    private RequestHandlerFactory requestHandlerFactory;


    public Server(int port, RequestHandlerFactory requestHandlerFactory) {
        try {
            serverSocket = new ServerSocket(port);
            this.requestHandlerFactory = requestHandlerFactory;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void start() {
        new Thread(this).start();
    }

    private String extractStringFromSocket(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return bufferedReader.readLine();
    }

    private void writeToSocket(Socket socket, String response) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(response);
    }

    private Runnable doTask(Socket socket) {
        Runnable task = ()-> {
            try {
                String stringRequest = extractStringFromSocket(socket);
                RequestHandler handler = requestHandlerFactory.createHandler(stringRequest);
                if (handler != null) {
                    String response = handler.handle(stringRequest);
                    writeToSocket(socket, response);
                } else {
                    writeToSocket(socket, requestHandlerFactory.getErrorMessage());
                }
            } catch (IOException e) {
                LOGGER.error(e.toString());
            } finally {
                closeSocket(socket);
            }
        };
        return task;
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Got a connection");
                executorService.execute(doTask(socket));
            } catch (Exception e) {
                LOGGER.error(e.toString());
            } finally {
                executorService.shutdownNow();
            }
        }
    }
}

