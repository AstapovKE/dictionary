package com.kastapov.server;

import com.kastapov.server.service.PropertiesHolderService;
import com.kastapov.server.service.SocketClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: kastapov
 * Date: 23.08.2015
 */
public class Server {

    private PropertiesHolderService propertiesHolderService = PropertiesHolderService.getProperties();
    private final int port = propertiesHolderService.getInteger("server.port");

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println(String.format("Server started successfully on port: %s", port));
        Socket socket;
        while(true){
            socket = serverSocket.accept();
            Thread thread = new Thread(new SocketClientHandler(socket));
            thread.start();
        }
    }
}
