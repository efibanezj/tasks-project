package com.task.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskRequest {
    private String message;
    private int priority;
}
