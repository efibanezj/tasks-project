package com.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.config.exception.BusinessException;
import com.task.config.exception.ExceptionMessage;
import com.task.domain.TaskDO;
import com.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.task.config.exception.ExceptionEnum.MESSAGE_MISSING;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private ExceptionMessage exceptionMessage;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void updateTask_validData_success() throws Exception {

        String messageExpected = "Clean your house and my house";
        int priorityExpected = 2;
        Long id = 1L;

        TaskDO taskDO = TaskDO.builder().message(messageExpected).priority(priorityExpected).build();
        when(taskService.update(any())).thenReturn(taskDO);
        String requestJson=objectMapper.writeValueAsString(taskDO);

        mockMvc.perform(put("/tasks/{id}", id)
                .content(requestJson).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.priority").exists());
    }

    @Test
    void updateTask_missingDescription_error() throws Exception {

        int priorityExpected = 2;
        Long id = 1L;

        TaskDO taskDO = TaskDO.builder().priority(priorityExpected).build();
        when(taskService.update(any())).thenThrow(new BusinessException(MESSAGE_MISSING));
        String requestJson=objectMapper.writeValueAsString(taskDO);

        mockMvc.perform(put("/tasks/{id}", id)
                 .content(requestJson).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").hasJsonPath())
                .andExpect(jsonPath("$.errorCode").exists());
    }


    @Test
    void getTask_validData_success() throws Exception {

        String messageExpected = "Clean house";
        int priorityExpected = 1;
        Long id = 1L;

        TaskDO taskDO = TaskDO.builder().message(messageExpected).priority(priorityExpected).build();
        when(taskService.get(any())).thenReturn(taskDO);

        mockMvc.perform(get("/tasks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(messageExpected))
                .andExpect(jsonPath("$.priority").value(priorityExpected));
    }

    @Test
    void getTask_missingId_error() throws Exception {

        String messageExpected = "Clean house";
        int priorityExpected = 1;
        Long id = null;

        mockMvc.perform(get("/tasks/{id}", id))
                .andExpect(status().isNotFound());
    }


}