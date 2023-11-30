package com.anand.service;

import java.util.List;

import com.anand.entity.Task;

public interface TaskService {
    Task findById(Long id);
    
    List<Task> findAll();

    Task save(Task task);

    void deleteById(Long id);

   
}

