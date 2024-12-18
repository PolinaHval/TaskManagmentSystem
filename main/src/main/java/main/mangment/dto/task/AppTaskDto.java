package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import main.mangment.model.Priority;
import main.mangment.model.Status;

import java.util.List;


/**
 * DTO для представления задачи.
 *
 * <p>Класс AppTaskDto используется для передачи данных задачи,
 * включая заголовок, описание, статус, приоритет, автора и исполнителя,
 * а также список комментариев.</p>
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "AppTaskDto для задачи")
public class AppTaskDto {

  String heider;
  String description;
  Status status;
  Priority priority;
  String authorUser;
  String executor;
  List<CommentDto> comments;
}
