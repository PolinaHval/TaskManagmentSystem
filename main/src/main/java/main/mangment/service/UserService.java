package main.mangment.service;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.users.CreateUserDto;
import main.mangment.exception.IncorrectDataException;
import main.mangment.exception.NotFoundException;
import main.mangment.model.Role;
import main.mangment.model.User;
import main.mangment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final HashPassword hashPassword;
  private final UserRepository userRepository;

  /**
   * Получение пользователя по его идентификатору.
   *
   * @param id идентификатор пользователя
   * @return найденный пользователь
   * @throws NotFoundException если пользователь не найден
   */
  public User getUser(long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Пользователь с id " + id + " не найден"));
  }

  /**
   * Получение пользователя по его электронной почте.
   *
   * @param email электронная почта пользователя
   * @return найденный пользователь
   * @throws NotFoundException если пользователь не найден
   */
  public User getUserEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException("Пользователь с email " + email + " не найден"));

  }

  /**
   * Аутентификация пользователя по электронной почте и паролю.
   *
   * @param email    электронная почта пользователя
   * @param password пароль пользователя
   * @return аутентифицированный пользователь
   * @throws NotFoundException       если пользователь не найден
   * @throws IncorrectDataException  если неверные учетные данные
   */
  public User login(final String email, final String password) {
    final User user = userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException("Пользователь с email " + email + " не найден"));

    if(validatePassword(password, user.getPassword())){
      return user;
    }
    throw new IncorrectDataException("Неверный логин или пароль!");
  }

  /**
   * Получение списка всех пользователей.
   *
   * @return список пользователей
   */
  public List<User> findUsers() {
    return userRepository.findAll();
  }

  /**
   * Хеширование пароля.
   *
   * @param password пароль для хеширования
   * @return хешированный пароль
   */
  private String hashingPassword(String password){
    return hashPassword.hashingPassword(password);
  }

  /**
   * Проверка корректности пароля.
   *
   * @param password      введенный пароль
   * @param hashedPassword хешированный пароль
   * @return true, если пароль корректен; false в противном случае
   */
  public boolean validatePassword(String password, String hashedPassword) {
    return hashPassword.validatePassword(password, hashedPassword);
  }

  /**
   * Создание нового пользователя.
   *
   * @param createUserDto DTO с данными для создания пользователя
   * @param role          роль для нового пользователя
   * @return созданный пользователь
   */
  @Transactional
  public User createUser(CreateUserDto createUserDto, Role role){
    final User user = User.builder()
        .email(createUserDto.getEmail())
        .password(hashingPassword(createUserDto.getPassword()))
        .role(role)
        .build();
    userRepository.save(user);
    return user;
  }

  /**
   * Получение пользователя из Principal.
   *
   * @param principal объект Principal, содержащий информацию о текущем пользователе
   * @return найденный пользователь
   * @throws NotFoundException если пользователь не найден
   */
  public User getUserFromPrincipal(Principal principal) {
    return userRepository.findByEmail(principal.getName())
        .orElseThrow(() -> new NotFoundException("Пользователь с email " + principal.getName() + " не найден"));
  }
}
