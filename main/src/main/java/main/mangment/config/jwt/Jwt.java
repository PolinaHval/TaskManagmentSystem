package main.mangment.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Класс Jwt предназначен для работы JWT.
 * Он обеспечивает функциональность для генерации токенов, валидации
 * токенов и извлечения адреса электронной почты из токена.
 * @component что позволяет Spring автоматически регистрировать его как компонент в контексте приложения.
 *
 */
@Component
public class Jwt {

  /**
   * Секретный ключ для подписи JWT, внедряется из файла application.yaml
   */
  @Value("$(jwtSecret)")
  private String jwtSecret;

  /**
   * Генерирует JWT token с временем жизни 15 минут
   * @param email адрес электронной почты для использования в токене
   * @return сгенерированный JWT как строка
   */
  public String generateToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  /**
   * Проверяет на валидация токена
   * @param token JWT для проверки
   * @return true, если токен действительный, в случае наоборот - false
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      System.out.println("Token expired.");
    } catch (UnsupportedJwtException unsEx) {
      System.out.println("Unsupported token.");
    } catch (MalformedJwtException mjEx) {
      System.out.println("Malformed token.");
    } catch (SignatureException sEx) {
      System.out.println("Invalid signature.");
    } catch (Exception e) {
      System.out.println("Token validation error: " + e.getMessage());
    }

    return false;
  }

  /**
   * Получает почты из токена
   * @param token для извлечения email
   * @return адрес электронной почты, извлеченный из токена.
   */
  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
