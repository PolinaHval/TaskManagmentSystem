package main.managment.service;

import main.mangment.converter.ConverterUser;
import main.mangment.model.Role;
import main.mangment.model.User;
import main.mangment.repository.UserRepository;
import main.mangment.service.HashPassword;
import main.mangment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = UserService.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private HashPassword hashPassword;

  @MockBean
  private UserRepository userRepository;

  @Test
  void getUserEmail() {
    Role role = new Role();
    role.setId(1);
    role.setName("admin");
    final User user = new User("Евгений", hashPassword.hashingPassword("123"), role);

    given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
    User returnedUser = userService.getUserEmail(user.getEmail());

    Assertions.assertEquals(returnedUser, user);
  }

  @Test
  public void findUsers() {
    List<User> users = new ArrayList<>();
    users.add(new User("Евгений", "123", new Role(1L, "admin")));

    given(userRepository.findAll()).willReturn(users);
    List<User> expected = userService.findUsers();

    Assertions.assertEquals(expected, users);

    verify(userRepository).findAll();
  }
}
