package com.task.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    TASK_NOT_FOUND(1001),
    MESSAGE_MISSING(1002);

    private final int value;

}
