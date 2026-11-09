package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // READ
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // CREATE
    @PostMapping
    public Task createTask(@RequestBody TaskRequest request) {
        return taskService.createTask(request.title(), request.description(), request.status());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequest request) {
        try {
            Task updated = taskService.updateTask(id, request.title(), request.description(), request.status());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

record TaskRequest(String title, String description, String status) {}