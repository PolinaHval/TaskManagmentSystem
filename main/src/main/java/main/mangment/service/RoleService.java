package main.mangment.service;

import lombok.AllArgsConstructor;
import main.mangment.exception.NotFoundException;
import main.mangment.model.Role;
import main.mangment.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис RoleService предоставляет бизнес-логику для управления ролями.
 *
 * <p>Сервис включает в себя метод для получения роли.</p>
 */
@Service
@AllArgsConstructor
public class RoleService {

  private RoleRepository roleRepository;

  /**
   * Получает роль по заданному имени.
   *
   * @param roleName имя роли
   * @return роль с указанным именем
   */
  public Role getRole(String roleName){
    return roleRepository.getRoleByName(roleName);
  }
}
