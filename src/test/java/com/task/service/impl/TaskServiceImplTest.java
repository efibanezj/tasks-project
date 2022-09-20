package com.task.service.impl;

import com.task.config.exception.BusinessException;
import com.task.config.exception.ExceptionEnum;
import com.task.config.exception.NotFoundException;
import com.task.domain.TaskDO;
import com.task.repository.TaskRepository;
import com.task.service.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskServiceImplTest {

    public TaskServiceImpl taskService;

    @Autowired
    @SpyBean
    public TaskRepository repository;

    @BeforeEach
    void setUp() {
        TaskMapper mapper = Mappers.getMapper(TaskMapper.class);
        taskService = new TaskServiceImpl(repository, mapper);
    }

    @Test
    void updateTask_missingMessage_error() {
        TaskDO taskDO = new TaskDO();
        BusinessException exception = assertThrows(BusinessException.class, () ->
                taskService.update(taskDO));
        assertThat(exception.getErrorCode()).isEqualTo(ExceptionEnum.MESSAGE_MISSING.getValue());
    }

    @Test
    void updateTask_idNotExist_error() {
        TaskDO taskDO = TaskDO.builder().message("new message").priority(2).id(12L).build();
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                taskService.update(taskDO));
        assertThat(exception.getErrorCode()).isEqualTo(ExceptionEnum.TASK_NOT_FOUND.getValue());
    }

    @Test
    void updateTask_idExist_success() {
        TaskDO taskDO = TaskDO.builder().message("new message").priority(2).id(1L).build();
        TaskDO taskDOUpdated = taskService.update(taskDO);
        assertThat(taskDOUpdated.getMessage()).isEqualTo("new message");
        assertThat(taskDOUpdated.getPriority()).isEqualTo(2);
    }

    @Test
    void getTask_notExist_error() {

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                taskService.get(12323L));
        assertThat(exception.getErrorCode()).isEqualTo(ExceptionEnum.TASK_NOT_FOUND.getValue());
    }

    @Test
    void getTask_exist_success() {

        TaskDO taskDO = taskService.get(1L);
        assertThat(taskDO.getMessage()).isEqualTo("Clean the house");
        assertThat(taskDO.getPriority()).isEqualTo(1);
    }
}