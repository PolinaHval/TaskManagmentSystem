package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Schema(description = "CredentialsUserDto для авторизации пользователя")
public class CredentialsUserDto {

  @NotBlank(message = "Email is empty")
  String email;

  @NotBlank(message = "Password is empty")
  String password;
}
