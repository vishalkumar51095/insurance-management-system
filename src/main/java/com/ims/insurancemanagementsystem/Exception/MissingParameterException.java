package com.ims.insurancemanagementsystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingParameterException extends RuntimeException {
    private final String paramName;

    public MissingParameterException(String paramName) {
        super("Required request parameter '" + paramName + "' is missing");
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public String toString() {
        return "MissingParameterException{" +
                "paramName='" + paramName + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

