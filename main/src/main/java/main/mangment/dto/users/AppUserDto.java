package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * DTO для представления пользователя.
 *
 * <p>Класс AppUserDto используется для передачи данных пользователя,
 * включая идентификатор, почта, пароль, роль.</p>
 */
@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "AppUserDto для пользователя")
public class AppUserDto {

  long id;
  String email;
  String password;
  String role;
}
