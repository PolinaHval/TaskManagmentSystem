package main.mangment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Класс Comment представляет собой сущность комментария, который может быть
 * связан с задачей и автором.
 *
 * <p>Комментарий содержит текст, дату написания, а также ссылки на задачу, к которой он
 * относится, и автора комментария.</p>
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "comments")
public class Comment {

  /**
   * Уникальный идентификатор комментария.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  /**
   * Текст комментария.
   */
  @Column(name = "comment")
  String comment;

  /**
   * Задача, к которой относится данный комментарий.
   */
  @ManyToOne()
  @JoinColumn(name = "task_id", referencedColumnName = "id")
  Task task;

  /**
   * Автор комментария.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "author_id")
   User author;

  /**
   * Дата написания комментария.
   */
  @Column(name = "write_date")
  private LocalDate writeDate;
}
