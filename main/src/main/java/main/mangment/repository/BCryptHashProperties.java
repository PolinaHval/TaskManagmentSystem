package main.mangment.repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;
import main.mangment.properties.HashProperties;
import main.mangment.service.HashPassword;
import org.springframework.stereotype.Repository;


import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Класс BCryptHashProperties реализует интерфейс HashPassword
 * и предоставляет методы для хеширования паролей и проверки их корректности с использованием
 * алгоритма BCrypt.
 *
 * <p>Данный класс использует настройки хеширования, предоставленные через
 * HashProperties, такие как секрет и сложность хеширования.</p>
 */
@Repository
@RequiredArgsConstructor
public class BCryptHashProperties implements HashPassword {

  private final HashProperties hashProperties;

  /**
   * Хеширует переданный пароль с использованием алгоритма BCrypt.
   *
   * @param password пароль, который необходимо захешировать
   * @return строка, представляющая захешированный пароль
   */
  @Override
  public String hashingPassword(String password) {
    final BCrypt.Hasher hasher =
        BCrypt.with(new SecureRandom(hashProperties.getSecret().getBytes(StandardCharsets.UTF_8)));
    return hasher.hashToString(hashProperties.getComplexity(), password.toCharArray());
  }

  /**
   * Проверяет, соответствует ли переданный пароль захешированному паролю.
   *
   * @param password пароль, который необходимо проверить
   * @param hashedPassword захешированный пароль для сравнения
   * @return true, если пароль соответствует захешированному, иначе false
   */
  @Override
  public boolean validatePassword(String password, String hashedPassword) {
    BCrypt.Result verify = BCrypt.verifyer().
        verify(password.getBytes(StandardCharsets.UTF_8), hashedPassword.getBytes(StandardCharsets.UTF_8));
    return verify.verified;
  }
}