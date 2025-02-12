package main.mangment.service;

import lombok.RequiredArgsConstructor;
import main.mangment.config.jwt.Jwt;
import main.mangment.model.User;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с JWT.
 */
@Service
@RequiredArgsConstructor
public class TokenService {

  private final Jwt jwtProvider;

  /**
   * Создание JWT для указанного пользователя.
   *
   * @param user пользователь, для которого создается токен
   * @return сгенерированный JWT
   */
  public String createToken(User user) {
   return jwtProvider.generateAccessToken(user.getEmail());
  }
}
