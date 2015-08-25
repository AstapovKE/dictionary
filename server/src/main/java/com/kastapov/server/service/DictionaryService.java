package com.kastapov.server.service;

import com.kastapov.model.enums.ResponseType;
import com.kastapov.model.request.Request;
import com.kastapov.model.response.Response;
import com.kastapov.server.dictionary.Dictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * User: kastapov
 * Date: 23.08.2015
 */
public enum DictionaryService {

    INSTANCE;

    private final Dictionary dictionary = Dictionary.INSTANCE;

    private PropertiesHolderService propertiesHolderService = PropertiesHolderService.getProperties();

    private final String wordAddedMsg = propertiesHolderService.getString("msg.word.added.successfully");
    private final String wordNotPresentMsg = propertiesHolderService.getString("msg.word.not.present");
    private final String definitionsDeletedMsg = propertiesHolderService.getString("msg.definitions.deleted");

    public Response handleRequest(final Request request) {
        String message = StringUtils.EMPTY;
        switch (request.getCommand()) {
            case ADD:
                message = addDefinitions(request);
                break;
            case GET:
                Set<String> defResp = getDefinitions(request);
                message = defResp != null ? StringUtils.join(defResp, '\n') : wordNotPresentMsg;
                break;
            case DELETE:
                message = deleteDefinitions(request);
                break;
        }
        Response response = new Response();
        response.setType(ResponseType.OK);
        response.setMessage(message);
        return response;
    }

    private String addDefinitions(Request request) {
        dictionary.add(request.getWord(), request.getDefinitions());
        return wordAddedMsg;
    }

    private Set<String> getDefinitions(Request request) {
        return dictionary.get(request.getWord());
    }

    private String deleteDefinitions(Request request) {
        boolean deleted = dictionary.delete(request.getWord(), request.getDefinitions());
        if (deleted) {
            return definitionsDeletedMsg;
        } else {
            return wordNotPresentMsg;
        }
    }
}
