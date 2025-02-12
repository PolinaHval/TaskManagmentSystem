package main.mangment.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для запроса на обновление JWT.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshJwtRequestDto {

  String refreshToken;
}
