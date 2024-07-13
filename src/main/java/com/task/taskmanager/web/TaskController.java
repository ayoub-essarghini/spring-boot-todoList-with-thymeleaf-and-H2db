package com.task.taskmanager.web;

import com.task.taskmanager.entities.Task;
import com.task.taskmanager.respositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {

    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String tasks(Model model)
    {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task/create")
    public String create(Model model)
    {
        model.addAttribute("task", new Task());
        return "create/create_task";
    }

    @PostMapping("/task/new")
    public String new_task(@ModelAttribute Task task)
    {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/edit/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            model.addAttribute("task", task);
            return "edit/edit_task"; // Make sure this view exists in templates/edit
        } else {
            model.addAttribute("error", "Task not found");
            return "error/404"; // Make sure this view exists in templates/error
        }
    }

    @PostMapping("/task/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task); // Save the updated task
        return "redirect:/tasks"; // Redirect to the tasks list after update
    }

    @GetMapping("/list-tasks")
    @ResponseBody
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
    @GetMapping("/delete")
    public String delete(Long id) {
        taskRepository.deleteById(id);
        return ("redirect:/tasks");
    }

}
