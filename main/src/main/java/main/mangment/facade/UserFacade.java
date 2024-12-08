package main.mangment.facade;

import lombok.AllArgsConstructor;
import main.mangment.dto.users.CreateUserDto;
import main.mangment.model.User;
import main.mangment.service.RoleService;
import main.mangment.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Фасад для работы с пользоваелями.
 *
 * <p>Класс UserFacade предоставляет методы для управления пользователями,
 * включая создание, получение пользоваеля, а также
 * получения списка пользователей.</p>
 */
@Component
@AllArgsConstructor
public class UserFacade {

  private UserService userService;
  private RoleService roleService;

  /**
   * Получает пользователя по идентификатору.
   *
   * @param id идентификатор пользователя
   * @return пользователь с указанным идентификатором
   */
  public User getUser(long id){
    return userService.getUser(id);
  }

  /**
   * Получает список пользователей.
   *
   * @return возвращает список пользователей
   */
  public List<User> findUsers() {
    return userService.findUsers();
  }

  /**
   * Создает новую задачу на основе предоставленного DTO и информации о пользователе.
   *
   * @param createUserDto DTO для создания пользователя
   * @return созданный пользователь
   */
  public User createUser(CreateUserDto createUserDto) {
    return userService.createUser(createUserDto, roleService.getRole(createUserDto.getRole()));
  }
}
