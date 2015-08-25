package com.kastapov.client.service;

import com.kastapov.client.model.ProgramArguments;
import com.kastapov.model.request.Request;
import com.kastapov.model.response.Response;

import java.io.*;
import java.net.Socket;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class ConnectionServiceImpl implements ConnectionService {

    private String hostname;
    private int port;
    Socket socketClient;

    public ConnectionServiceImpl(ProgramArguments programArguments) {
        this.hostname = programArguments.getAddress();
        this.port = programArguments.getPort();
    }

    @Override
    public Response processRequest(Request request) throws Exception {
        this.connect();
        this.sendRequest(request);
        return this.readResponse();
    }

    private void connect() throws Exception {
        try {
            socketClient = new Socket(hostname, port);
        } catch (Exception e) {
            throw new Exception(String.format("Cannot establish connection to the host %s:%s", hostname, port), e);
        }
    }

    private void sendRequest(Request request) throws Exception {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
            objectOutputStream.writeObject(request);
        } catch (IOException e) {
            throw new Exception(String.format("Error processing request to the server %s:%s", hostname, port), e);
        }
    }

    private Response readResponse() throws Exception {
        try {
            ObjectInputStream ois = new ObjectInputStream(socketClient.getInputStream());
            return (Response) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new Exception(String.format("Error getting response from the server %s:%s", hostname, port), e);
        }
    }
}
