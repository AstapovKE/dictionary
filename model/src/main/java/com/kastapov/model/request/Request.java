package com.kastapov.model.request;

import com.kastapov.model.enums.CommandType;

import java.io.Serializable;
import java.util.Set;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class Request implements Serializable {

    private static final Long serialVersionUID = 1L;

    private CommandType command;
    private String word;
    private Set<String> definitions;

    public Request(CommandType command, String word, Set<String> definitions) {
        this.command = command;
        this.word = word;
        this.definitions = definitions;
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
        return "Request{" +
                "command=" + command +
                ", word='" + word + '\'' +
                ", definitions=" + definitions +
                '}';
    }
}
