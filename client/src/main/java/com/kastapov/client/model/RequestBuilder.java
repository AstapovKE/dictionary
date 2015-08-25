package com.kastapov.client.model;

import com.kastapov.model.enums.CommandType;
import com.kastapov.model.request.Request;

import java.util.Set;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class RequestBuilder {
    private CommandType command;
    private String word;
    private Set<String> definitions;

    public RequestBuilder(ProgramArguments programArguments) {
        this.command = programArguments.getCommand();
        this.word = programArguments.getWord();
        this.definitions = programArguments.getDefinitions();
    }

    public RequestBuilder setCommand(CommandType command) {
        this.command = command;
        return this;
    }

    public RequestBuilder setWord(String word) {
        this.word = word;
        return this;
    }

    public RequestBuilder setDefinitions(Set<String> definitions) {
        this.definitions = definitions;
        return this;
    }

    public Request createRequest() {
        return new Request(command, word, definitions);
    }
}