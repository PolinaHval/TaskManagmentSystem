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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(name = "heider")
  String heider;

  @Column(name = "description")
  String description;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  Status status;

  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  Priority priority;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  User authorUser;

  @ManyToOne
  @JoinColumn(name = "executor_id")
  User executor;

  @OneToMany
  @JoinColumn(name = "task_id")
  private List<Comment> comment = new ArrayList<>();
}
