package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

   public final  TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {

        if(project.getProjectStatus()==null)
            project.setProjectStatus(Status.OPEN); //to avoid null pointer exception at project.status.value


        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);

    }

    @Override
    public void update(ProjectDTO object) {

        if(object.getProjectStatus()==null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }
        //behind the scenes when we update the project html changes values by setters since we don't have an option to update the status of the project we need to assign it to it's previous values to avoid null pointer exception

        super.update(object.getProjectCode(),object);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETED);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project->project.getAssignedManager().equals(manager))
                        .map(project -> {

                            List<TaskDTO> taskList = taskService.findTaskByManager(manager);
                            int completeTaskCount= (int) taskList.stream().filter(t-> t.getProject().equals(project) && t.getTaskStatus()==Status.COMPLETED).count();
                            int unfinishedTaskCounts = (int) taskList.stream().filter(t->t.getProject().equals(project) && t.getTaskStatus()!=Status.COMPLETED).count();

                            project.setCompleteTaskCounts(5);
                            project.setUnfinishedTaskCounts(3);


                            return project;
                        })



                        .collect(Collectors.toList());


        return projectList;
    }
}

