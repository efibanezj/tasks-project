package com.task.controller;


import com.sun.istack.NotNull;
import com.task.controller.request.UpdateTaskRequest;
import com.task.controller.response.GetTaskResponse;
import com.task.controller.response.UpdateTaskResponse;
import com.task.domain.TaskDO;
import com.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/{id}")
    public UpdateTaskResponse updateTask(@PathVariable(value = "id") Long id, @RequestBody UpdateTaskRequest request) {

        TaskDO taskToUpdate = TaskDO.builder().id(id).message(request.getMessage()).priority(request.getPriority()).build();
        TaskDO taskUpdated = taskService.update(taskToUpdate);
        return UpdateTaskResponse.builder().message(taskUpdated.getMessage()).priority(taskUpdated.getPriority()).build();
    }

    @GetMapping("/{id}")
    public GetTaskResponse getTask(@PathVariable(value = "id") @NotNull Long id) {
        TaskDO task = taskService.get(id);
        return GetTaskResponse.builder().message(task.getMessage()).priority(task.getPriority()).build();
    }
}
