package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import main.mangment.validation.ValidEmail;
import javax.validation.constraints.NotBlank;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "UpdateTaskDto для создания пользователя")
public class CreateUserDto {

  @ValidEmail
  @NotBlank(message = "Поле не может быть пустым")
  String email;

  @NotBlank(message = "Поле не может быть пустым")
  String password;

  @NotBlank(message = "Поле не может быть пустым")
  String role;
}
