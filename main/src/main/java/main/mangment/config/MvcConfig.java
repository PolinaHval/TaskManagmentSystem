package main.mangment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация, реализующая интерфейс WebMvcConfigurer.
 * Этот класс отвечает за настройку CORS для приложения.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final String allowedOrigin;

    /**
     * Конструктор, который инициализирует MvcConfig с заданным разрешенным источником.
     * @param allowedOrigin адрес разрешенного источника, считываемый из application.yaml
     */
    public MvcConfig(@Value("${allowed-origin}") final String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    /**
     * Настраивает параметры CORS для обработки запросов из других источников.
     * Данный метод позволяет всем источникам выполнять запросы к любым конечным точкам
     * приложения, если они соответствуют указанным параметрам.
     *
     * @param registry объект CorsRegistry, используемый для добавления конфигурации CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(allowedOrigin);
    }
}
