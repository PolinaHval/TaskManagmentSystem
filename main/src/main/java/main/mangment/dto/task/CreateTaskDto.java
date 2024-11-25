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

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "CommentDto для комментария")
public class CreateTaskDto {

  @NotBlank
  String heider;

  @NotBlank
  String description;

  Status status;

  Priority priority;

  @NotBlank
  String executorEmail;
}
