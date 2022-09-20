package com.task.service;

import com.task.domain.TaskDO;

public interface TaskService {

    /**
     * Method to update a task
     *
     * @param taskDO task to be updated
     * @return task updated
     */
    TaskDO update(TaskDO taskDO);

    /**
     * Method to get a task
     *
     * @return task information
     */
    TaskDO get(Long id);
}
