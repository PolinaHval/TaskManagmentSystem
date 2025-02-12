package main.mangment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import main.mangment.dto.users.AuthResultDto;
import main.mangment.dto.users.CredentialsUserDto;
import main.mangment.dto.users.RefreshJwtRequestDto;
import main.mangment.facade.AuthFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для аутентификации пользователей.
 *
 * <p>Этот контроллер предоставляет API для аутентификации пользователя по логину и паролю.
 * Доступен для всех пользователей и позволяет получать JWT-токен после успешной аутентификации.</p>
 *
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Контроллер для аутентификации", description = "Позволяет аутентифицировать пользователя по логину и паролю." +
    "Доступно для всех пользователей")
public class AuthController {

  private final AuthFacade authFacade;

  /**
   * Аутентификация пользователя.
   *
   * @param credentialsUserDto объект с учетными данными пользователя
   * @return результат аутентификации
   */
  @PostMapping()
  protected AuthResultDto authorize(@RequestBody final CredentialsUserDto credentialsUserDto) {
  return authFacade.login(credentialsUserDto.getEmail(), credentialsUserDto.getPassword());
 }

  /**
   * Выход пользователя из системы.
   *
   * @param userId идентификатор пользователя
   * @return ответ о выходе из системы
   */
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@RequestBody long userId) {
    authFacade.deleteByUserId(userId);
    return ResponseEntity.ok().body("User logged out");
  }

  /**
   * Обновление токена доступа.
   *
   * @param request объект с запросом на обновление токена
   * @return новый токен доступа
   */
  @PostMapping("refresh")
  protected AuthResultDto getNewRefreshToken(@RequestBody final RefreshJwtRequestDto request) {
    System.out.println(request.getRefreshToken());
    return authFacade.refresh(request.getRefreshToken());
  }
}
