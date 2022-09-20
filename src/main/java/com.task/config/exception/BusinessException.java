package com.task.config.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessException extends RuntimeException {

    private final int errorCode;

    public BusinessException(ExceptionEnum clientErrorCode) {
        super(clientErrorCode.name());
        this.errorCode = clientErrorCode.getValue();
    }
}
