package com.github.zjor.ext.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ControllerUtils {

    public static ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), message);
    }

    public static ResponseStatusException notFound(String message) {
        return new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()), message);
    }

    public static ResponseStatusException serverError(String message) {
        return new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
    }

}
