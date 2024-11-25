package main.mangment.service;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.task.UpdateTaskDto;
import main.mangment.dto.task.CreateCommentsDto;
import main.mangment.dto.task.CreateTaskDto;
import main.mangment.exception.NotFoundException;
import main.mangment.exception.NotEnoughRightsException;
import main.mangment.model.Comment;
import main.mangment.model.Priority;
import main.mangment.model.Status;
import main.mangment.model.Task;
import main.mangment.model.User;
import main.mangment.repository.CommentRepository;
import main.mangment.repository.TaskRepository;
import main.mangment.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.mangment.specification.TaskSpecification.findByAuthor;
import static main.mangment.specification.TaskSpecification.findByExecutor;
import static main.mangment.specification.TaskSpecification.findByPriority;
import static main.mangment.specification.TaskSpecification.findByStatus;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;

  public Task createTask(CreateTaskDto createTaskDto, User user, User userExecutor){
    Task task = Task.builder()
        .heider(createTaskDto.getHeider())
        .description(createTaskDto.getDescription())
        .authorUser(user)
        .priority(createTaskDto.getPriority())
        .status(createTaskDto.getStatus())
        .executor(userExecutor)
        .build();

    taskRepository.save(task);
    return task;
  }

  public void delete(long taskId, User user) {
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " + taskId +
            " не найдена"));
    if (user.getId() == task.getAuthorUser().getId()) {
      taskRepository.deleteById(taskId);
    } else {
      throw new NotEnoughRightsException("Невозможно удалить задачу. " +
          "Удалить задачу может только автор");
    }
  }

  public Task getTaskById(long taskId) {
    return taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " + taskId +
        " не найдена"));
  }

  public Task changeTaskStatus(long taskId, Status status, User user) {
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " + taskId +
            " не найдена"));
    if(user.getId() == task.getAuthorUser().getId()){
      task.setStatus(status);
      return taskRepository.save(task);
    } else {
      throw new NotEnoughRightsException("Невозможно обновить статус задачи. " +
          "Обновить статус задачи может только автор или исполнитель");
    }
  }

  public Task setExecutorTask(long taskId, User userExecutor, User userAuthor) {
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " + taskId +
            " не найдена"));
    if (userAuthor.getId() == task.getAuthorUser().getId()) {
      task.setExecutor(userExecutor);
      return taskRepository.save(task);
    } else {
      throw new NotEnoughRightsException("Невозможно назначить исполнителя задачи. " +
          "Назначить исполнителя может только автор задачи");
    }
  }

  public Task addComments(CreateCommentsDto createCommentsDto, User userAuthor) {
    Task task = taskRepository.findById(createCommentsDto.getTaskId())
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " +
            createCommentsDto.getTaskId() + " не найдена"));
    LocalDate dateNow = LocalDate.now();
    Comment comment = Comment.builder()
        .comment(createCommentsDto.getDescription())
        .task(task)
        .author(userAuthor)
        .writeDate(dateNow)
        .build();
    commentRepository.save(comment);
    return task;
  }

  public Page<Task> getTasksByFilters(Long authorId, Long executorId,
                                         Status status, Priority priority, Pageable pageable) {
    Specification<Task> specification = searchParametersToSpecification(authorId, executorId, status, priority);
    return taskRepository.findAll(specification, pageable);
  }

  private Specification<Task> searchParametersToSpecification(Long authorId, Long executorId,
                                                              Status status, Priority priority) {
    User author = new User();
    User executor = new User();

    if (authorId != null) {
      author = userRepository.findById(authorId)
          .orElseThrow(() -> new NotFoundException("Невозможно получить список задач. Пользователь с id "
              + authorId + " не найден"));
    }

    if (executorId != null) {
      executor = userRepository.findById(executorId)
          .orElseThrow(() -> new NotFoundException("Невозможно получить список задач. Пользователь с id "
              + executorId + " не найден"));
    }

    List<Specification<Task>> specifications = new ArrayList<>();
    specifications.add(authorId == null ? null : findByAuthor(author));
    specifications.add(executorId == null ? null : findByExecutor(executor));
    specifications.add(status == null ? null : findByStatus(status));
    specifications.add(priority == null ? null : findByPriority(priority));

    return specifications.stream().filter(Objects::nonNull).reduce(Specification::and)
        .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
  }

  public Task updateTask(UpdateTaskDto updateTaskDto, long taskId, User user) {
    final Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Невозможно обновить задачу. Задача с id " +
            taskId + " не найдена"));

    if (user.getId() == task.getAuthorUser().getId()) {
      task.setHeider(updateTaskDto.getHeider());
      task.setDescription(updateTaskDto.getDescription());
      task.setPriority(updateTaskDto.getPriority());
      taskRepository.save(task);
      return task;
    } else {
      throw new NotEnoughRightsException("Невозможно обновить статус задачу. " +
          "Обновить данные задачи может только автор");
    }
  }
}
