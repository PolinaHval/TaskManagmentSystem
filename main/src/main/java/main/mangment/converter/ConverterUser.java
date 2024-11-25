package main.mangment.converter;
import lombok.RequiredArgsConstructor;
import main.mangment.dto.users.AppUserDto;
import main.mangment.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConverterUser {

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

  public AppUserDto toDto(User user) {
    if (user == null) {
      return null;
    }

    return new AppUserDto(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName());
  }
}
