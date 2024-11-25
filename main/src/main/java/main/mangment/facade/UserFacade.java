package main.mangment.facade;

import lombok.AllArgsConstructor;
import main.mangment.dto.users.CreateUserDto;
import main.mangment.model.User;
import main.mangment.service.RoleService;
import main.mangment.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class UserFacade {

  private UserService userService;
  private RoleService roleService;

  public User getUser(long id){
    return userService.getUser(id);
  }

  public List<User> findUsers() {
    return userService.findUsers();
  }

  public User createUser(CreateUserDto createUserDto) {
    return userService.createUser(createUserDto, roleService.getRole(createUserDto.getRole()));
  }
}
