package com.kastapov.client.service;

import com.kastapov.client.model.ProgramArguments;
import com.kastapov.model.enums.CommandType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class ArgumentsParserServiceImpl implements ArgumentsParserService {

    private static final String HOST_VALIDATION_REGEX = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    private static final int MIN_ARGS_LENGTH = 4;
    private static final int SERVER_ADDRESS_ARG_INDEX = 0;
    private static final int SERVER_PORT_ARG_INDEX = 1;
    private static final int COMMAND_ARG_INDEX = 2;
    private static final int WORD_ARG_INDEX = 3;
    private static final int DEFINITION_ARG_INDEX = 4;

    @Override
    public ProgramArguments parseArgs(String... args) throws Exception {
        if (args.length < MIN_ARGS_LENGTH) {
            throw new Exception("Program arguments must contain al least 3 parameters: address, port, command, word.");
        }
        ProgramArguments arguments = new ProgramArguments();
        arguments.setAddress(getHost(args));
        arguments.setPort(getPort(args));
        arguments.setCommand(getCommand(args));
        arguments.setWord(getWord(args));
        arguments.setDefinitions(getDefinitions(args));

        if (arguments.getCommand() == CommandType.ADD && arguments.getDefinitions().isEmpty()) {
            throw new Exception("You must provide at least one word definition.");
        }

        return arguments;
    }

    private String getHost(String... args) throws Exception {
        String host = args[SERVER_ADDRESS_ARG_INDEX];
        if (!host.matches(HOST_VALIDATION_REGEX)) {
            throw new Exception("Provided server address is wrong.");
        }
        return host;
    }

    private int getPort(String... args) throws Exception {
        String port = args[SERVER_PORT_ARG_INDEX];
        try {
            return Integer.valueOf(port);
        } catch (NumberFormatException e) {
            throw new Exception("Unable to parse server port value.");
        }
    }

    private CommandType getCommand(String... args) throws Exception {
        String commandName = args[COMMAND_ARG_INDEX].toUpperCase();
        CommandType command;
        try {
            command = CommandType.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            throw new Exception("Command prompted is not recognised, try to use commands: add, get, delete.", e);
        }
        return command;
    }

    private String getWord(String... args) {
        return args[WORD_ARG_INDEX];
    }

    private Set<String> getDefinitions(String[] args) throws Exception {
        return new HashSet<>(Arrays.asList(Arrays.copyOfRange(args, DEFINITION_ARG_INDEX, args.length)));
    }
}
