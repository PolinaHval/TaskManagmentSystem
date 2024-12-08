package main.mangment.service;

import lombok.RequiredArgsConstructor;
import main.mangment.dto.users.CreateUserDto;
import main.mangment.exception.IncorrectDataException;
import main.mangment.exception.NotFoundException;
import main.mangment.model.Role;
import main.mangment.model.User;
import main.mangment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final HashPassword hashPassword;
  private final UserRepository userRepository;

  public User getUser(long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Пользователь с id " + id + " не найден"));
  }

  public User getUserEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException("Пользователь с email " + email + " не найден"));

  }

  public User login(final String email, final String password) {
    final User user = userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException("Пользователь с email " + email + " не найден"));

    if(validatePassword(password, user.getPassword())){
      return user;
    }
    throw new IncorrectDataException("Неверный логин или пароль!");
  }

  public List<User> findUsers() {
    return userRepository.findAll();
  }

  private String hashingPassword(String password){
    return hashPassword.hashingPassword(password);
  }

  public boolean validatePassword(String password, String hashedPassword) {
    return hashPassword.validatePassword(password, hashedPassword);
  }

  @Transactional
  public User createUser(CreateUserDto createUserDto, Role role){
    final User user = User.builder()
        .email(createUserDto.getEmail())
        .password(hashingPassword(createUserDto.getPassword()))
        .role(role)
        .build();
    userRepository.save(user);
    return user;
  }

  public User getUserFromPrincipal(Principal principal) {
    return userRepository.findByEmail(principal.getName())
        .orElseThrow(() -> new NotFoundException("Пользователь с email " + principal.getName() + " не найден"));
  }
}
