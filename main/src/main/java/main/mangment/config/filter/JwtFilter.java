package main.mangment.config.filter;

import lombok.RequiredArgsConstructor;
import main.mangment.config.jwt.Jwt;
import main.mangment.config.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * Фильтр JWT для проверки и аутентификации пользователей на основе токена.
 * Этот фильтр извлекает JWT из заголовка запроса и проверяет его на валидность.
 * @component аннотация позволяет Spring управлять этим классом как компонентом приложения.
 * @requiredArgsConstructor генерирует конструктор для всех финальных полей.
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  public static final String AUTHORIZATION = "Authorization";
  private final Jwt jwtProvider;
  private final AuthService customUserDetailsService;

  /**
   * Метод фильтрации, проверяющий наличие и валидность JWT в запросе.
   * @param servletRequest  запрос сервлета
   * @param servletResponse ответ сервлета
   * @throws IOException если происходит ошибка ввода-вывода
   * @throws ServletException если происходит ошибка обработки сервлета
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    String token = getTokenFromRequest((HttpServletRequest)servletRequest);

    if(token != null && jwtProvider.validateToken(token)){
      String userEmail = jwtProvider.getEmailFromToken(token);
      UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userEmail);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails,
          null, customUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  /**
   * Извлекает JWT токен из запроса.
   * @param request HttpServletRequest, из которого извлекается токен
   * @return Строка токена или null, если токен отсутствует
   */
  private String getTokenFromRequest(HttpServletRequest request){

    String bearer = request.getHeader(AUTHORIZATION);

    if (bearer != null && bearer.startsWith("Bearer ")){
      return bearer.substring(7);
    }
    return null;
  }
}
