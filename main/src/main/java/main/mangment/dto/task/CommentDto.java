package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

/**
 * DTO для представления комментария.
 *
 * <p>Класс CommentDto используется для передачи данных комментария,
 * включая комментарий, автора и дату создания комментария.</p>
 *
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "CommentDto для комментария")
public class CommentDto {

  String comment;
  String author;
  LocalDate writeDate;
}
