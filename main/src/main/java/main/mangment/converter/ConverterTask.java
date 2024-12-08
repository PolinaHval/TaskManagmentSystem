package main.mangment.converter;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.task.AppTaskDto;
import main.mangment.model.Task;
import org.springframework.stereotype.Component;

/**
 * Конвертер ConverterTask отвечает за преобразование объектов Task
 * в объекты AppTaskDto.
 */
@Component
@RequiredArgsConstructor
public class ConverterTask {
  private final CommentConvert commentConvert;

  /**
   * Преобразует объект User в объект AppUserDto.
   *
   * @param task задача, которую необходимо преобразовать
   * @return объект AppTaskDto или null
   */
  public AppTaskDto toDto(Task task) {
    if (task == null) {
      return null;
    }

    return new AppTaskDto(task.getHeider(),task.getDescription(), task.getStatus(), task.getPriority(),
        task.getAuthorUser().getEmail(), task.getExecutor().getEmail(), commentConvert.toDto(task.getComment()));
  }
}
