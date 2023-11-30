package com.anand.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.entity.Task;
import com.anand.repository.TaskRepository;
import com.anand.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    
}

