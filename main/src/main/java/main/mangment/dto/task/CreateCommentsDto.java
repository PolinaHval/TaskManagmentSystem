package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * DTO для создания комментария.
 *
 * <p>Класс CreateCommentsDto передает данные для создания комментария,
 * а именно индентификатор задачи и описание.</p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CommentDto для создания комментария")
public class CreateCommentsDto {

  /**
   * Индентификатор задачи
   *
   * <p>Описывает индентификатор задачи с минимальным значением 1.</p>
   *
   */
  @Min(value = 1, message = "Id должно быть больше 1")
  private long taskId;

  /**
   * Описание
   *
   * <p>Описание не может быть пустым</p>
   *
   */
  @NotBlank
  private String description;
}
