package com.todo.service.impl;

import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isPresent()) {
            Task existing = optionalTask.get();
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setCompleted(task.isCompleted());
            return taskRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}