package main.mangment.repository;

import main.mangment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Интерфейс UserRepository предоставляет методы для работы с сущностью User.
 *
 * <p>Этот интерфейс расширяет JpaRepository, что позволяет использовать
 * стандартные методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * над пользователями.</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Находит пользователя по указанной почте.
   *
   * @param email адрес электронной почты пользователя
   * @return Optional содержащий найденного пользователя, если он существует
   */
  Optional<User> findByEmail(String email);
}
