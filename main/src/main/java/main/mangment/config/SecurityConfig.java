package main.mangment.config;

import lombok.RequiredArgsConstructor;
import main.mangment.config.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Конфигурация безопасности приложения.
 *
 * <p>Этот класс настраивает параметры безопасности</p>
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  /**
   * Настраивает цепочку фильтров безопасности для обработки HTTP-запросов.
   *
   * <p>Этот метод отключает базовую аутентификацию и CSRF-защиту,
   * устанавливает политику создания сессий в безсессионный режим,
   * а также определяет правила авторизации для различных конечных точек API.</p>
   * <p>Доступ к Swagger UI и конечной точке аутентификации разрешен для всех пользователей,
   * в то время как все остальные запросы требуют аутентификации.</p>
   *
   * @param http объект HttpSecurity, используемый для настройки параметров безопасности
   * @return цепочка фильтров безопасности
   * @throws Exception если происходит ошибка при настройке безопасности
   */
  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http
        .cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/v1/auth/**").permitAll()
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    http.headers().frameOptions().disable();
    
    return http.build();
  }

  /**
   * Настраивает кодирование паролей при помощи BCrypt .
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
