package com.task.config.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotFoundException extends RuntimeException {

    private final int errorCode;

    public NotFoundException(ExceptionEnum clientErrorCode) {
        super(clientErrorCode.name());
        this.errorCode = clientErrorCode.getValue();
    }
}
