package com.kastapov.client.service;

import com.kastapov.model.request.Request;
import com.kastapov.model.response.Response;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public interface ConnectionService {

    Response processRequest(Request request) throws Exception;
}
