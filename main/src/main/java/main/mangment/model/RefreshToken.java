package main.mangment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Сущность для хранения refresh-токена.
 */
@Entity
@Table(name = "refresh")
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "token_id")
  long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  @Column(name = "ref_token", unique = true)
  String refreshToken;

  @Column(name = "exp_date")
  Instant expDate;
}
