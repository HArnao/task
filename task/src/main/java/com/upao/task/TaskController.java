package com.upao.task;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        tasks.add(task);
    }

    @DeleteMapping("/{index}")
    public void deleteTask(@PathVariable int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

}
