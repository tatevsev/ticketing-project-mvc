package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createTask(Model  model){
        model.addAttribute("task",new TaskDTO());
        model.addAttribute("tasks",taskService.findAll());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());

        return "/task/create";
    }


    @PostMapping("/create")
    public String insertTask(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteById(id);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model){

        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }


/*
    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task){

        task.setId(taskId);
        taskService.update(task);
        return "redirect:/task/create";
    }
*/

    @PostMapping("/update/{Id}")
    public String updateTask(TaskDTO task){
      //  public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task){
//as task has field Lond id, spring allows us to skip @Pathvariable and will bind {id} with the field

            taskService.update(task);
        return "redirect:/task/create";
    }

}
