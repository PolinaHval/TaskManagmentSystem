package main.mangment.facade;

import lombok.AllArgsConstructor;
import main.mangment.dto.task.CreateCommentsDto;
import main.mangment.dto.task.CreateTaskDto;
import main.mangment.dto.task.UpdateTaskDto;
import main.mangment.model.Priority;
import main.mangment.model.Status;
import main.mangment.model.Task;
import main.mangment.model.User;
import main.mangment.service.TaskService;
import main.mangment.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@AllArgsConstructor
public class TaskFacade {

  private final TaskService taskService;
  private final UserService userService;

  public Task createTask(CreateTaskDto createTaskDto, Principal principal) {
   User user = userService.getUserFromPrincipal(principal);
   User userExecutor = userService.getUserEmail(createTaskDto.getExecutorEmail());
   return taskService.createTask(createTaskDto, user, userExecutor);
  }

  public void delete(long taskId, Principal principal) {
    User user = userService.getUserFromPrincipal(principal);
    taskService.delete(taskId,user);
  }

  public Task getTaskById(long taskId) {
    return taskService.getTaskById(taskId);
  }

  public Task changeTaskStatus(long taskId, Status status, Principal principal) {
    User user = userService.getUserFromPrincipal(principal);
    return taskService.changeTaskStatus(taskId,status,user);
  }

  public Task setExecutorTask(long taskId, long executorId, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    User userExecutor = userService.getUser(executorId);
    return  taskService.setExecutorTask(taskId, userExecutor, userAuthor);
  }

  public Task addComments(CreateCommentsDto createCommentsDto, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    return taskService.addComments(createCommentsDto,userAuthor);
  }

 public Page<Task> getTasksByFilters (Long authorId, Long executorId,
                                      Status status, Priority priority, Pageable pageable){
    return taskService.getTasksByFilters(authorId,executorId,status,priority,pageable);
 }

  public Task updateTask(UpdateTaskDto updateTaskDto, long taskId, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    return taskService.updateTask(updateTaskDto,taskId, userAuthor);
  }
}
