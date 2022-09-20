package com.task.service.mapper;

import com.task.domain.TaskDO;
import com.task.repository.entity.Task;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDO entityToDomain(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskDO.TaskDOBuilder taskDO = TaskDO.builder();

        taskDO.id( entity.getId() );
        taskDO.message( entity.getMessage() );
        taskDO.priority( entity.getPriority() );

        return taskDO.build();
    }
}
