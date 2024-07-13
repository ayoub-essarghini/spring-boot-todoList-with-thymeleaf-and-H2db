package com.task.taskmanager;

import com.task.taskmanager.entities.Task;
import com.task.taskmanager.respositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository taskRepository) {
        return args -> {
            taskRepository.save(new Task(null,"Task 1","description 1"));
            taskRepository.save(new Task(null,"Task 2","description 2"));
            taskRepository.save(new Task(null,"Task 3","description 3"));
            taskRepository.save(new Task(null,"Task 4","description 4"));
            taskRepository.save(new Task(null,"Task 5","description 5"));

            taskRepository.findAll().forEach(t->System.out.println(t.getTitle()));
        };
    }

}
