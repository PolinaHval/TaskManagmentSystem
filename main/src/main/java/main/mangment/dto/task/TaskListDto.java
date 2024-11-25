package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "TaskListDto для списка задач")
public class TaskListDto {

    List<AppTaskDto> listTask;
    int totalPages;
    long totalElements;
}
