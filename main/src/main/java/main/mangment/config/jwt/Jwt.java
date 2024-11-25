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

@Component
@Slf4j
public class Jwt {

  @Value("$(jwtSecret)")
  private String jwtSecret;

  public String generateToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.info("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.info("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.info("Malformed jwt");
    } catch (SignatureException sEx) {
      log.info("Invalid signature");
    } catch (Exception e) {
      log.info("invalid token");
    }
    return false;
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
