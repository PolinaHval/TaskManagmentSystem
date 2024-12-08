package main.mangment.converter;
import lombok.RequiredArgsConstructor;
import main.mangment.dto.users.AppUserDto;
import main.mangment.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертер ConverterUser отвечает за преобразование объектов User
 * в объекты AppUserDto.
 *
 * <p>Класс предоставляет методы для преобразования как одного пользователя, так и списка пользователей.</p>
 */
@Component
@RequiredArgsConstructor
public class ConverterUser {

  /**
   * Преобразует список объектов User в список объектов AppUserDto.
   *
   * @param users список пользователей, который необходимо преобразовать
   * @return список объектов AppUserDto, соответствующий переданному списку пользователей,
   *         или null
   */
  public List<AppUserDto> toDto(List<User> users) {
    if (users == null) {
      return null;
    }

    final List<AppUserDto> list = new ArrayList<>(users.size());

    for (User user : users) {
      list.add(AppUserDto.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
          .role(user.getRole().getName()).build());
    }
    return list;
  }

  /**
   * Преобразует объект User в объект AppUserDto.
   *
   * @param user пользователь, который необходимо преобразовать
   * @return объект  AppUserDto, соответствующий переданному пользователю,
   *         или null
   */
  public AppUserDto toDto(User user) {
    if (user == null) {
      return null;
    }

    return new AppUserDto(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName());
  }
}
