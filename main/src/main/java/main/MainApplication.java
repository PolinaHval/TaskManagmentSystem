package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Основной класс приложения MainApplication, который запускает Spring Boot приложение.
 *
 * <p>Этот класс является точкой входа в приложение и включает аннотации для автоматической
 * конфигурации Spring и управления транзакциями.</p>
 */
@SpringBootApplication
@EnableTransactionManagement
public class MainApplication {

  /**
   * Главный метод приложения, который запускает Spring Boot приложение.
   *
   * @param args аргументы командной строки, переданные при запуске приложения
   */
  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
}
