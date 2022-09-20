package com.task.controller.response;

import com.task.domain.TaskDO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetTaskResponse {

    private String message;
    private int priority;
}
