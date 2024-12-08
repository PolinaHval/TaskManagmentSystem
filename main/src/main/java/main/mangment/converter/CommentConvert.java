package main.mangment.converter;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.task.CommentDto;
import main.mangment.model.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертер CommentConvert отвечает за преобразование объектов Comment
 * в объекты CommentDto.
 */
@Component
@RequiredArgsConstructor
public class CommentConvert {

  /**
   * Преобразует список объектов Comment в список объектов CommentDto.
   *
   * @param comments список пользователей, который необходимо преобразовать
   * @return список объектов CommentDto или null
   */
  public List<CommentDto> toDto(List<Comment> comments ) {
    if (comments == null) {
      return null;
    }

    final List<CommentDto> list = new ArrayList<>(comments.size());

    for (Comment comment : comments) {
      list.add(CommentDto.builder().comment(comment.getComment()).author(comment.getAuthor().getEmail())
          .writeDate(comment.getWriteDate()).build());
    }
    return list;
  }
}
