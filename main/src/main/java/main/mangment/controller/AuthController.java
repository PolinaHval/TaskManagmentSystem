package main.mangment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import main.mangment.config.jwt.Jwt;
import main.mangment.dto.users.AuthResultDto;
import main.mangment.dto.users.CredentialsUserDto;
import main.mangment.model.User;
import main.mangment.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Контроллер для аутентификации", description = "Позволяет аутентифицировать пользователя по логину и паролю." +
    "Доступно для всех пользователей")
public class AuthController {

  private final UserService userService;
  private final Jwt jwt;

  @PostMapping()
  protected AuthResultDto authorize(@RequestBody final CredentialsUserDto credentialsUserDto){
    User user = userService.login(credentialsUserDto.getEmail(), credentialsUserDto.getPassword());
    String token = jwt.generateToken(user.getEmail());
    return new AuthResultDto(token);
 }
}
