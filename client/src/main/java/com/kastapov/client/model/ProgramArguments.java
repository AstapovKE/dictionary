package com.kastapov.client.model;

import com.kastapov.model.enums.CommandType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class ProgramArguments implements Serializable {

    private String address;
    private int port;
    private CommandType command;
    private String word;
    private Set<String> definitions = new HashSet<String>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public CommandType getCommand() {
        return command;
    }

    public void setCommand(CommandType command) {
        this.command = command;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(Set<String> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return "ProgramArguments{" +
                "address='" + address + '\'' +
                ", port=" + port +
                ", command='" + command + '\'' +
                ", word='" + word + '\'' +
                ", definitions=" + definitions +
                '}';
    }
}