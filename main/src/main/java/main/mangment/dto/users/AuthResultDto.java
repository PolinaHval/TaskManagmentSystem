package main.mangment.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "AuthResultDto для токена")
public class AuthResultDto {
  String token;
}
