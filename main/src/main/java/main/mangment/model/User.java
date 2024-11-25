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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(name = "email")
  String email;

  @Column(name = "password")
  String password;

  @ManyToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  Role role;

  public User(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
