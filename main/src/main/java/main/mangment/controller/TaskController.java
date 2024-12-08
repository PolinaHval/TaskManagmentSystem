package main.mangment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import main.mangment.converter.ConverterTask;
import main.mangment.dto.task.AppTaskDto;
import main.mangment.dto.task.CreateCommentsDto;
import main.mangment.dto.task.CreateTaskDto;
import main.mangment.dto.task.UpdateTaskDto;
import main.mangment.facade.TaskFacade;
import main.mangment.model.Priority;
import main.mangment.model.Status;
import main.mangment.model.Task;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер TaskController предоставляет REST API для управления задачами.
 *
 * <p>Контроллер позволяет создавать новые задачи, редактировать и удалять существующие задачи,
 * получать задачи по идентификатору или списком, менять статус задачи, назначать исполнителей
 * и добавлять комментарии к задачам.</p>
 *
 * <p>Доступ к методам контроллера ограничен в зависимости от ролей пользователей.</p>
 */
@RestController
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@RequestMapping("/api/v1/task")
@Tag(name = "Контроллер для работы с задачами", description = "Позволяет создавать новые задачи, редактировать" +
    "  и удалять существующие, получать задачи по id или списком, менять статус задачи, назначать исполнителя" +
    " и добавлять комментарии к задаче")
public class TaskController {
  private final ConverterTask converterTask;
  private final TaskFacade taskFacade;

  /**
   * Создает новую задачу.
   *
   * @param createTaskDto объект, содержащий данные для создания задачи
   * @param principal информация о текущем аутентифицированном пользователе
   * @return созданная задача в виде объекта AppTaskDto
   */
  @PreAuthorize("hasRole('admin')")
  @PostMapping
  @Operation(summary = "Создание задачи. Доступно всем аутентифицированным пользователям")
  protected AppTaskDto createTask(@Valid @RequestBody final CreateTaskDto createTaskDto, Principal principal){
    final Task task = taskFacade.createTask(createTaskDto, principal);
    return converterTask.toDto(task);
  }

  /**
   * Удаляет задачу по идентификатору.
   *
   * @param taskId идентификатор задачи, которую необходимо удалить
   * @param principal информация о текущем аутентифицированном пользователе
   */
  @PreAuthorize("hasRole('admin')")
  @DeleteMapping("/{taskId}")
  @Operation(summary = "Удаление задачи. Доступно только автору задачи")
  public void delete(@PathVariable  long taskId, Principal principal) {
    taskFacade.delete(taskId, principal);
  }

  /**
   * Получает задачу по идентификатору.
   *
   * @param taskId идентификатор задачи
   * @return задача в виде объекта AppTaskDto
   */
  @GetMapping("/{taskId}")
  @Operation(summary = "Получение задачи по id. Доступно всем аутентифицированным пользователям")
  public AppTaskDto getTaskById(@PathVariable long taskId) {
    return converterTask.toDto(taskFacade.getTaskById(taskId));
  }

  /**
   * Изменяет статус задачи.
   *
   * @param taskId идентификатор задачи, статус которой необходимо изменить
   * @param status новый статус задачи
   * @param principal информация о текущем аутентифицированном пользователе
   * @return обновленная задача в виде объекта AppTaskDto
   */
  @PutMapping("/{taskId}/changeStatus")
  @Operation(summary = "Изменение статуса задачи. Доступно автору или исполнителю")
  public AppTaskDto changeTaskStatus(@PathVariable long taskId, @RequestParam(value = "status") Status status,
                                     Principal principal) {
    return converterTask.toDto(taskFacade.changeTaskStatus(taskId, status, principal));
  }

  /**
   * Назначает исполнителя для задачи.
   *
   * @param taskId идентификатор задачи, для которой назначается исполнитель
   * @param executorId идентификатор пользователя, который будет назначен исполнителем
   * @param principal информация о текущем аутентифицированном пользователе
   * @return задача с назначенным исполнителем в виде объекта AppTaskDto
   */
  @PreAuthorize("hasRole('admin')")
  @PutMapping("/{taskId}/setExecutor")
  @Operation(summary = "Назначение исполнителя задачи. Доступно только автору задачи")
  public AppTaskDto setExecutorTask(@PathVariable long taskId,
                                    @RequestParam(value = "executorId")  long executorId,
                                    Principal principal) {
    return converterTask.toDto(taskFacade.setExecutorTask(taskId, executorId, principal));
  }

  /**
   * Добавляет комментарий к задаче.
   * @param createCommentsDto объект, содержащий данные для создания комментария
   * @param principal информация о текущем аутентифицированном пользователе
   * @return задача с добавленным комментарием в виде объекта AppTaskDto
   */
  @PostMapping("/addComment")
  @Operation(summary = "Добавление комментария к задачи. Доступно всем аутентифицированным пользователям")
  public AppTaskDto addComment(@RequestBody CreateCommentsDto createCommentsDto, Principal principal) {
    return converterTask.toDto(taskFacade.addComments(createCommentsDto, principal));
  }

  /**
   * Получает список задач с возможностью фильтрации.
   * <p>Автором для получения своих задач необходимо заполнить authorId.
   * Исполнителям для получения своих задач необходимо заполнить executorId.
   * Дополнительно возможно выбрать параметры status и priority.
   * В случае пропуска всех параметров будут получены все задачи.</p>
   * @param authorId идентификатор автора задач (необязательный)
   * @param executorId идентификатор исполнителя задач (необязательный)
   * @param status статус задач (необязательный)
   * @param priority приоритет задач (необязательный)
   * @param page номер страницы для пагинации (по умолчанию 0)
   * @param size размер страницы для пагинации (по умолчанию 5)
   * @return список задач в виде объектов AppTaskDto
   */
  @GetMapping("/listTask")
  @Operation(summary = "Получение списка задач. Доступно всем аутентифицированным пользователям",
      description = "Автором для получения своих задач необходимо заполнить authorId. " +
          "Исполнителям для получения своих задач необходимо заполнить executorId. " +
          "Дополнительно возможно выбрать параметры status и priority " +
          "В случае пропуска всех параметров будут получены все задачи")
  public List<AppTaskDto> getTaskAllOrByFilters(@RequestParam(value = "authorId", required = false) Long authorId,
                                        @RequestParam(value = "executorId", required = false) Long executorId,
                                        @RequestParam(value = "status", required = false) Status status,
                                        @RequestParam(value = "priority", required = false) Priority priority,
                                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
    return taskFacade.getTasksByFilters(authorId, executorId, status, priority, PageRequest.of(page, size))
        .stream().map(converterTask::toDto).collect(Collectors.toList());
  }

  /**
   * Обновление задачи.
   *
   * <p>Метод позволяет обновить существующую задачу по её идентификатору. Доступ к этому методу
   * ограничен пользователям с ролью 'admin' и автору задачи.</p>
   *
   * @param taskId Идентификатор задачи, которую необходимо обновить.
   * @param updateTaskDto DTO с данными для обновления задачи.
   * @param principal Объект, представляющий аутентифицированного пользователя, который является автором задачи.
   * @return AppTaskDto обновленная задача.
   */
  @PreAuthorize("hasRole('admin')")
  @PatchMapping("/{taskId}")
  @Operation(summary = "Обновление задачи. Доступно аутентифицированным пользователям: автору")
  public AppTaskDto update(@PathVariable long taskId, @RequestBody UpdateTaskDto updateTaskDto, Principal principal) {
    return converterTask.toDto(taskFacade.updateTask(updateTaskDto, taskId, principal));
  }
}
