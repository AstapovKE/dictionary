package com.kastapov.server;

import java.io.IOException;

/**
 * User: kastapov
 * Date: 23.08.2015
 */
public class Launcher {

    public static void main(String[] args) {
        try {
            Server socketServer = new Server();
            socketServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
