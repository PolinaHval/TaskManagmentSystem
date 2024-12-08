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

/**
 * Контроллер для работы с пользователями.
 *
 * <p>Этот контроллер предоставляет API для создания новых пользователей, получения информации
 * о пользователях по их идентификаторам,а также получения списка всех пользователей.
 * Доступ к методам контроллера ограничен пользователям с ролью 'admin'.</p>
 */
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

  /**
   * Создание нового пользователя.
   *
   * <p>Метод принимает объект CreateUserDto с данными для создания пользователя и возвращает
   * объект AppUserDto, представляющий созданного пользователя.</p>
   *
   * @param createUserDto DTO с данными для создания пользователя.
   * @return AppUserDto созданного пользователя.
   */
  @PostMapping("/create")
  @Operation(summary = "Создание пользователя")
  protected AppUserDto createUser(@Valid @RequestBody final CreateUserDto createUserDto){
    final User user = userFacade.createUser(createUserDto);
    return converterUser.toDto(user);
  }

  /**
   * Получение пользователя по его идентификатору.
   *
   * <p>Метод принимает идентификатор пользователя и возвращает объект AppUserDto, представляющий
   * запрашиваемого пользователя.</p>
   * @param id Идентификатор пользователя.
   * @return AppUserDto запрашиваемого пользователя.
   */
  @GetMapping("/{id}")
  @Operation(summary = "Получения пользователя по ID")
  protected AppUserDto getUser(@PathVariable final long id){
    final User user = userFacade.getUser(id);
    return converterUser.toDto(user);
  }

  /**
   * Получение списка всех пользователей.
   *
   * <p>Метод возвращает список объектов AppUserDto, представляющих всех пользователей.</p>
   *
   * @return Список AppUserDto всех пользователей.
   */
  @GetMapping("/all")
  @Operation(summary = "Получения списка всех пользователей")
  protected List<AppUserDto> getUserAll(){
    return converterUser.toDto(userFacade.findUsers());
  }
}
