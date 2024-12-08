package main.mangment.config.service;

import lombok.RequiredArgsConstructor;
import main.mangment.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис аутентификации пользователей, реализующий интерфейс UserDetailsService.
 * Этот сервис отвечает за загрузку данных о пользователе по его электронной почте.
 * @requiredArgsConstructor генерирует конструктор для всех финальных полей.
 */
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  private final UserService userService;

  /**
   * Загружает данные о пользователе по указанной электронной почте.
   * @param email адрес электронной почты пользователя
   * @return объект UserDetails, содержащий информацию о пользователе
   * @throws UsernameNotFoundException если пользователь с указанной электронной почтой не найден
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     final main.mangment.model.User user = userService.getUserEmail(email);
    return new User(user.getEmail(), user.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));
  }
}
