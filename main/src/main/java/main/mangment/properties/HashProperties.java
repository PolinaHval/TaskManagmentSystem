package main.mangment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Класс HashProperties представляет собой конфигурационный класс, который
 * содержит свойства, связанные с хешированием.
 *
 * <p>Этот класс загружает параметры конфигурации из файла application.yaml , такие как секрет для хеширования и
 * уровень сложности хеширования.</p>
 */
@Configuration
@Getter
@Setter
public class HashProperties {

  /**
   * Секретный ключ, используемый для хеширования.
   */
  @Value("${hashSecret}")
  private String secret;

  /**
   * Уровень сложности хеширования.
   */
  @Value("${complexity}")
  private int complexity;
}
