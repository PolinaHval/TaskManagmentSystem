package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
/**
 * DTO для аутентификации пользователя.
 *
 * <p>Класс CreateUserDto передает данные для аутентификации пользователя,
 * включая почту, пароль.</p>
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "CredentialsUserDto для авторизации пользователя")
public class CredentialsUserDto {

  /**
   * Роль.
   *
   * <p>Роль не может быть пустой.</p>
   */
  @NotBlank(message = "Email is empty")
  String email;

  /**
   * Пароль.
   *
   * <p>Пароль не может быть пустым.</p>
   */
  @NotBlank(message = "Password is empty")
  String password;
}
