package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import main.mangment.model.Priority;
import main.mangment.model.Status;

import javax.validation.constraints.NotBlank;

/**
 * DTO для создания задачии.
 *
 * <p>Класс CreateTaskDto передает данные для создания задачи,
 * а именно заголовок, описание, статус, приоритет и почта исполнителя.</p>
 *
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "CommentDto для комментария")
public class CreateTaskDto {

  /**
   * Заголовок
   *
   * <p>Заголовок не может быть пустым.</p>
   *
   */
  @NotBlank
  String heider;

  /**
   * Описание
   *
   * <p>Описание не может быть пустым.</p>
   *
   */
  @NotBlank
  String description;

  Status status;

  Priority priority;

  /**
   * Почта исполнителя.
   *
   * <p>Почта исполнителя не может быть пустой.</p>
   *
   */
  @NotBlank
  String executorEmail;
}
