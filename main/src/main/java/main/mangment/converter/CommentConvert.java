package main.mangment.converter;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.task.CommentDto;
import main.mangment.model.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentConvert {

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
