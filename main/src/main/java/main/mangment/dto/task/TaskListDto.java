package main.mangment.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для списка задач.
 *
 * <p>Класс TaskListDto передает данные для списка задач,
 * а именно список задач, всего страниц, всего элементов.</p>
 *
 */
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "TaskListDto для списка задач")
public class TaskListDto {

    List<AppTaskDto> listTask;
    int totalPages;
    long totalElements;
}
