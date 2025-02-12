package main.mangment.repository;

import main.mangment.model.RefreshToken;
import main.mangment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с refresh-токенами.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  RefreshToken findByRefreshToken(String token);
  @Modifying
  int deleteByUser(User user);
}
