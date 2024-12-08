package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import main.mangment.model.Priority;

import javax.validation.constraints.NotBlank;

/**
 * DTO для обновления задачи.
 *
 * <p>Класс UpdateTaskDto передает данные для обновления задачи,
 * а именно загооловок, описание, приоритет.</p>
 *
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "UpdateTaskDto для обновления задачи")
public class UpdateTaskDto {

  /**
   * Заголовок
   *
   * <p>Заголовок не может быть пустым</p>
   *
   */
  @NotBlank
  String heider;

  /**
   * Описание
   *
   * <p>Описание не может быть пустым</p>
   *
   */
  @NotBlank
  String description;

  Priority priority;
}
