package com.kastapov.server.service;

import com.kastapov.model.enums.ResponseType;
import com.kastapov.model.request.Request;
import com.kastapov.model.response.Response;

import java.io.*;
import java.net.Socket;

/**
 * User: kastapov
 * Date: 23.08.2015
 */
public class SocketClientHandler implements Runnable {

    private DictionaryService dictionaryService = DictionaryService.INSTANCE;

    private Socket socket;

    public SocketClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRequest() throws IOException {
        Response response = new Response();
        try {
            Request request = getRequest();
            System.out.println("Handling request " + request.toString());
            response = dictionaryService.handleRequest(request);
        } catch (Exception e) {
            response.setType(ResponseType.ERROR);
            response.setMessage(e.getMessage());
        } finally {
            sendResponse(response);
            socket.close();
        }
    }

    private Request getRequest() throws Exception {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return  (Request) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Exception("Error getting response from the client. Class not found.");
        }
    }

    private void sendResponse(Response response) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
