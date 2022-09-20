package com.task.service.mapper;

import com.task.domain.TaskDO;
import com.task.repository.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDO entityToDomain(Task entity);
}
