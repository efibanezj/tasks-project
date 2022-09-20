package com.task.service.impl;

import com.task.config.exception.BusinessException;
import com.task.config.exception.NotFoundException;
import com.task.domain.TaskDO;
import com.task.repository.TaskRepository;
import com.task.repository.entity.Task;
import com.task.service.TaskService;
import com.task.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.task.config.exception.ExceptionEnum.MESSAGE_MISSING;
import static com.task.config.exception.ExceptionEnum.TASK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    @Transactional
    @CachePut(value="task", key="#taskDO.id")
    public TaskDO update(final TaskDO taskDO) {

        if (StringUtils.isEmpty(taskDO.getMessage())) {
            throw new BusinessException(MESSAGE_MISSING);
        }
        Task entityTask = repository.findById(taskDO.getId()).orElseThrow(
                () -> new NotFoundException(TASK_NOT_FOUND));

        entityTask.setMessage(taskDO.getMessage());
        entityTask.setPriority(taskDO.getPriority());

        return taskDO;
    }

    @Override
    @Cacheable(value = "task", key = "#id")
    @Transactional(readOnly = true)
    public TaskDO get(final Long id) {
        Task entityTask = repository.findById(id).orElseThrow(
                () -> new NotFoundException(TASK_NOT_FOUND));
        return mapper.entityToDomain(entityTask);
    }
}
