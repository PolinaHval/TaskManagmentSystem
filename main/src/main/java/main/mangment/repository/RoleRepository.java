package main.mangment.repository;

import main.mangment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс RoleRepository предоставляет методы для работы с сущностью роли .
 *
 * <p>Этот интерфейс расширяет JpaRepository, что позволяет использовать
 * стандартные методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * над ролями.</p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

  /**
   * Находит роль по имени.
   *
   * @param roleName имя роли
   * @return роль
   */
  Role getRoleByName(String roleName);
}
