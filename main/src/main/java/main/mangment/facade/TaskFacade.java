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

/**
 * Фасад для работы с задачами.
 *
 * <p>Класс TaskFacade предоставляет методы для управления задачами,
 * включая создание, удаление, обновление и получение задач, а также
 * управление комментариями и исполнителями.</p>
 */

@Component
@AllArgsConstructor
public class TaskFacade {

  private final TaskService taskService;
  private final UserService userService;

  /**
   * Создает новую задачу на основе предоставленного DTO и информации о пользователе.
   *
   * @param createTaskDto DTO для создания задачи
   * @param principal информация о текущем пользователе
   * @return созданная задача
   */
  public Task createTask(CreateTaskDto createTaskDto, Principal principal) {
   User user = userService.getUserFromPrincipal(principal);
   User userExecutor = userService.getUserEmail(createTaskDto.getExecutorEmail());
   return taskService.createTask(createTaskDto, user, userExecutor);
  }

  /**
   * Удаляет задачу по идентификатору.
   *
   * @param taskId идентификатор задачи
   * @param principal информация о текущем пользователе
   */
  public void delete(long taskId, Principal principal) {
    User user = userService.getUserFromPrincipal(principal);
    taskService.delete(taskId,user);
  }

  /**
   * Получает задачу по идентификатору.
   *
   * @param taskId идентификатор задачи
   * @return задача с указанным идентификатором
   */
  public Task getTaskById(long taskId) {
    return taskService.getTaskById(taskId);
  }

  /**
   * Изменяет статус задачи.
   *
   * @param taskId идентификатор задачи
   * @param status новый статус задачи
   * @param principal информация о текущем пользователе
   * @return обновленная задача
   */
  public Task changeTaskStatus(long taskId, Status status, Principal principal) {
    User user = userService.getUserFromPrincipal(principal);
    return taskService.changeTaskStatus(taskId,status,user);
  }

  /**
   * Устанавливает исполнителя для задачи.
   *
   * @param taskId идентификатор задачи
   * @param executorId идентификатор исполнителя
   * @param principal информация о текущем пользователе
   * @return обновленная задача с установленным исполнителем
   */
  public Task setExecutorTask(long taskId, long executorId, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    User userExecutor = userService.getUser(executorId);
    return  taskService.setExecutorTask(taskId, userExecutor, userAuthor);
  }

  /**
   * Добавляет комментарий к задаче.
   *
   * @param createCommentsDto DTO для создания комментария
   * @param principal информация о текущем пользователе
   * @return задача с добавленным комментарием
   */
  public Task addComments(CreateCommentsDto createCommentsDto, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    return taskService.addComments(createCommentsDto,userAuthor);
  }

  /**
   * Получает список задач по заданным фильтрам.
   *
   * @param authorId идентификатор автора задач, возможен null
   * @param executorId идентификатор исполнителя задач, возможен null
   * @param status статус задач, возможен null
   * @param priority приоритет задач, возможен null
   * @param pageable параметры пагинации
   * @return страница задач, соответствующих заданным фильтрам
   */
  public Page<Task> getTasksByFilters (Long authorId, Long executorId,
                                      Status status, Priority priority, Pageable pageable){
    return taskService.getTasksByFilters(authorId,executorId,status,priority,pageable);
 }

  /**
  * Обновляет задачу на основе предоставленного DTO и идентификатора задачи.
  *
  * @param updateTaskDto DTO для обновления задачи
  * @param taskId идентификатор задачи
  * @param principal информация о текущем пользователе
  * @return обновленная задача
  */
  public Task updateTask(UpdateTaskDto updateTaskDto, long taskId, Principal principal) {
    User userAuthor = userService.getUserFromPrincipal(principal);
    return taskService.updateTask(updateTaskDto,taskId, userAuthor);
  }
}
