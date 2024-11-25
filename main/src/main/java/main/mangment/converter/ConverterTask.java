package main.mangment.converter;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.task.AppTaskDto;
import main.mangment.model.Task;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterTask {
  private final CommentConvert commentConvert;
  public AppTaskDto toDto(Task task) {
    if (task == null) {
      return null;
    }

    return new AppTaskDto(task.getHeider(),task.getDescription(), task.getStatus(), task.getPriority(),
        task.getAuthorUser().getEmail(), task.getExecutor().getEmail(), commentConvert.toDto(task.getComment()));
  }
}
