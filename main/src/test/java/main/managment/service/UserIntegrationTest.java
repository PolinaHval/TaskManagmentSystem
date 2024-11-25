package main.managment.service;
import main.mangment.model.Role;
import main.mangment.model.User;
import main.mangment.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIntegrationTest extends AbstractIntegrationUsersTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserByLoginAfterCreatingTest() {
        User user = new User("Natasha5@mail.ru", "123", new Role(1L, "admin"));
        userRepository.save(user);

        Optional<User> expected = Optional.of(user);
        Optional<User> result = userRepository.findByEmail(user.getEmail());

        assertEquals(result, expected);
    }

    @Test
    public void findUsersTest() {
        List<User> users = userRepository.findAll();

        assertThat(users)
                .hasSize(3);
    }
}
