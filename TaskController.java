package com.anand.controller;

import com.anand.entity.Task;
import com.anand.service.TaskService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = service.findById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.findAll();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
    	 task.setCreatedAt(task.getCreatedAt());
         task.setUpdatedAt(task.getUpdatedAt());
        Task savedTask = service.save(task);
        logger.info("Received POST request to create a task.");
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
    
   @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody(required = false) Task task) throws NotFoundException {
        if (task == null) {
            return ResponseEntity.badRequest().build(); 
        }

        Task existingTask = service.findById(id);
        if (existingTask != null) {
            task.setId(id);
            Task updatedTask = service.save(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

   
    
}
