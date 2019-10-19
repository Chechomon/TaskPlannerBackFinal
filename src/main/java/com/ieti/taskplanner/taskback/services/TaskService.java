package com.ieti.taskplanner.taskback.services;

import com.ieti.taskplanner.taskback.model.Task;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface TaskService {
    List<Task> getTaskList();
    Task getTaskById(String id);
    List<Task> getTasksByUserId(String userId);
    void assignTaskToUser(Task task, String userId);
    void removeTask(String taskId);
    void updateTask(Task task);
}
