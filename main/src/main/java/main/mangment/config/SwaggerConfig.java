package main.mangment.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger для системы управления задачами.
 *
 * <p>Этот класс настраивает OpenAPI для предоставления документации
 * и описания API системы управления задачами. Он включает информацию о версии,
 * описании и требованиях к безопасности.</p>
 * <p>Используется схема безопасности JWT для аутентификации пользователей.</p>
 */
@OpenAPIDefinition(info = @Info(title = "Task Management System", version = "1.0",
    description = "Система управления задачими"),
    security = {@SecurityRequirement(name = "bearerToken")}
)
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@Configuration
public class SwaggerConfig {
}
