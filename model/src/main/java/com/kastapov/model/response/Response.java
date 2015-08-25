package com.kastapov.model.response;

import com.kastapov.model.enums.ResponseType;

import java.io.Serializable;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public class Response implements Serializable {

    private static final Long serialVersionUID = 1L;

    ResponseType type;
    String message;

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
