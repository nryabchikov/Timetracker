package by.krainet.timetracker.service;

import by.krainet.timetracker.exception.ProjectNotFoundException;
import by.krainet.timetracker.exception.TaskNotFoundException;
import by.krainet.timetracker.model.Project;
import by.krainet.timetracker.model.Task;
import by.krainet.timetracker.repository.ProjectRepository;
import by.krainet.timetracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public List<Task> getAllTasksByProjectId(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return taskRepository.findByProjectId(projectId);
    }

    public Task getTaskByIdAndProjectId(Long taskId, Long projectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Task addTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        task.setProject(project);
        return taskRepository.save(task);
    }

    public Task updateTask(Long projectId, Long taskId, Task taskDetails) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }

    public Task moveTaskToAnotherProject(Long projectId, Long taskId, Long newProjectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        Project newProject = projectRepository.findById(newProjectId)
                .orElseThrow(() -> new ProjectNotFoundException(newProjectId));
        task.setProject(newProject);
        return taskRepository.save(task);
    }

    public void deleteTask(Long projectId, Long taskId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findByIdAndProjectId(taskId, projectId).orElseThrow(() -> new TaskNotFoundException(taskId));
        taskRepository.delete(task);
    }
}

