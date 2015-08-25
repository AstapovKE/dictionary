package com.kastapov.client;

import com.kastapov.client.model.ProgramArguments;
import com.kastapov.client.model.RequestBuilder;
import com.kastapov.client.service.ArgumentsParserService;
import com.kastapov.client.service.ArgumentsParserServiceImpl;
import com.kastapov.client.service.ConnectionService;
import com.kastapov.client.service.ConnectionServiceImpl;
import com.kastapov.model.enums.ResponseType;
import com.kastapov.model.request.Request;
import com.kastapov.model.response.Response;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class Launcher {

    public static void main(String... args) {
        try {
            ArgumentsParserService parser = new ArgumentsParserServiceImpl();
            ProgramArguments programArguments = parser.parseArgs(args);

            ConnectionService connectionService = new ConnectionServiceImpl(programArguments);
            RequestBuilder requestBuilder = new RequestBuilder(programArguments);
            Response response = connectionService.processRequest(requestBuilder.createRequest());

            if (response.getType().equals(ResponseType.OK)) {
                System.out.println(response.getMessage());
            } else {
                System.err.println(response.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
