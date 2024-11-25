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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(name = "comment")
  String comment;

  @ManyToOne()
  @JoinColumn(name = "task_id", referencedColumnName = "id")
  Task task;

  @ManyToOne(optional = false)
  @JoinColumn(name = "author_id")
   User author;

  @Column(name = "write_date")
  private LocalDate writeDate;
}
