package com.task.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskResponse {

    private String message;
    private int priority;
}
