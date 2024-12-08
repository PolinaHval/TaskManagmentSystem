package main.mangment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Task представляет собой сущность задачи, которая может быть
 * связана с автором, исполнителем и списком комментариев.
 *
 * <p>Задача содержит идентификатор, загооловок, описание, статус, приоритет, автора, исполнителя
 * и список комментариев</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

  /**
   * Идентификатор.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  /**
   * Заголовок.
   */
  @Column(name = "heider")
  String heider;

  /**
   * Описание.
   */
  @Column(name = "description")
  String description;

  /**
   * Статус.
   */
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  Status status;

  /**
   * Приоритет.
   */
  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  Priority priority;

  /**
   * Автор.
   */
  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  User authorUser;

  /**
   * Исполнитель.
   */
  @ManyToOne
  @JoinColumn(name = "executor_id")
  User executor;

  /**
   * Список комментариев.
   */
  @OneToMany
  @JoinColumn(name = "task_id")
  private List<Comment> comment = new ArrayList<>();
}
