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
import javax.persistence.Table;

/**
 * Класс Role представляет собой сущность роль
 *
 * <p>Роль содержит идентификатор и имя.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

  /**
   * Уникальный идентификатор комментария.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  /**
   * Имя роли.
   */
  @Column(name = "role")
  String name;
}
