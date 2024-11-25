package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CommentDto для создания комментария")
public class CreateCommentsDto {

  @Min(value = 1, message = "Id должно быть больше 1")
  private long taskId;

  @NotBlank
  private String description;
}
