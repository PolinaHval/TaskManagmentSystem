package main.mangment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import main.mangment.converter.ConverterUser;
import main.mangment.dto.users.AppUserDto;
import main.mangment.dto.users.CreateUserDto;
import main.mangment.facade.UserFacade;
import main.mangment.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("api/v1/admin")
@PreAuthorize("hasRole('admin')")
@AllArgsConstructor
@Tag(name = "Контроллер для работы с пользователями", description = "Позволяет создавать новых пользоввателей," +
    "получать пользователей по id или списком. Доступно пользователям с ролью admin")
public class AdminController {

  private final UserFacade userFacade;
  private final ConverterUser converterUser;

  @PostMapping("/create")
  @Operation(summary = "Создание пользователя")
  protected AppUserDto createUser(@Valid @RequestBody final CreateUserDto createUserDto){
    final User user = userFacade.createUser(createUserDto);
    return converterUser.toDto(user);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получения пользователя по ID")
  protected AppUserDto getUser(@PathVariable final long id){
    final User user = userFacade.getUser(id);
    return converterUser.toDto(user);
  }

  @GetMapping("/all")
  @Operation(summary = "Получения списка всех пользователей")
  protected List<AppUserDto> getUserAll(){
    return converterUser.toDto(userFacade.findUsers());
  }
}
