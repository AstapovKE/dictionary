package com.kastapov.client.service;

import com.kastapov.client.model.ProgramArguments;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public interface ArgumentsParserService {

    ProgramArguments parseArgs(String... args) throws Exception;
}
