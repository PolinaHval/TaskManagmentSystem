package main.mangment.facade;

import lombok.AllArgsConstructor;
import main.mangment.dto.users.AuthResultDto;
import main.mangment.exception.AuthException;
import main.mangment.model.RefreshToken;
import main.mangment.model.User;
import main.mangment.service.RefreshTokenService;
import main.mangment.service.TokenService;
import main.mangment.service.UserService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Фасад аутентификации, который управляет логикой аутентификации и обновления токенов.
 */

@Component
@AllArgsConstructor
public class AuthFacade {

  private UserService userService;
  private TokenService tokenService;
  private RefreshTokenService refreshTokenService;

  /**
   * Аутентификация пользователя с предоставленными учетными данными.
   * @param email электронная почта пользователя
   * @param password пароль пользователя
   * @return результат аутентификации
   */
  public AuthResultDto login (String email, String password) {
    User user = userService.login(email, password);
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
    return new AuthResultDto(tokenService.createToken(user), refreshToken.getRefreshToken());
  }

  /**
   * Обновление токена доступа на основе предоставленного refresh-токена.
   *
   * @param refreshToken refresh-токен для обновления
   * @return новый токен доступа
   */
  public AuthResultDto refresh (String refreshToken) {
    RefreshToken token = refreshTokenService.findByRefreshToken(refreshToken);
    if(token != null && refreshTokenService.verifyExpiration(token) != null) {
      User user = token.getUser();
      String jwt = tokenService.createToken(user);
      return new AuthResultDto(jwt,token.getRefreshToken());
    }
    throw new AuthException("Невалидный токен");
  }

  /**
   * Удаление refresh-токена по идентификатору пользователя.
   *
   * @param userId идентификатор пользователя
   * @return количество удаленных записей
   */
  @Transactional
  public int deleteByUserId(long userId) {
    return refreshTokenService.deleteByUserId(userService.getUser(userId));
  }
}
