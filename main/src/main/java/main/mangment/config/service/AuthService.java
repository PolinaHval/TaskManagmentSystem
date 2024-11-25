package main.mangment.config.service;

import lombok.RequiredArgsConstructor;
import main.mangment.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     final main.mangment.model.User user = userService.getUserEmail(email);
    return new User(user.getEmail(), user.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));
  }
}
