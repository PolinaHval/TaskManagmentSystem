package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

/**
 * DTO для представления результата аутентификации.
 *
 * <p>Этот класс используется для передачи токена, полученного после успешной аутентификации пользователя.</p>
 */
@Value
@Schema(description = "AuthResultDto для токена")
public class AuthResultDto {

  /**
   * Токен аутентификации.
   */
  String token;
}
