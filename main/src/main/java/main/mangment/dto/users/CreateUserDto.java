package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import main.mangment.validation.ValidEmail;
import javax.validation.constraints.NotBlank;

/**
 * DTO для создания пользователя.
 *
 * <p>Класс CreateUserDto передает данные для создания пользователя,
 * включая почту, пароль, роль.</p>
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "UpdateTaskDto для создания пользователя")
public class CreateUserDto {

  /**
   * Почта.
   *
   * <p>Почта не может быть пустой, а также должна быть соответсвовать валидации.</p>
   */
  @ValidEmail
  @NotBlank(message = "Поле не может быть пустым")
  String email;

  /**
   * Пароль.
   *
   * <p>Пароль не может быть пустым.</p>
   */
  @NotBlank(message = "Поле не может быть пустым")
  String password;

  /**
   * Роль.
   *
   * <p>Роль не может быть пустой.</p>
   */
  @NotBlank(message = "Поле не может быть пустым")
  String role;
}
