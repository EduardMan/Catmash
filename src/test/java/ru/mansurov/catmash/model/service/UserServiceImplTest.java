package ru.mansurov.catmash.model.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mansurov.catmash.model.Role;
import ru.mansurov.catmash.model.User;
import ru.mansurov.catmash.repo.UserRepo;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void addUser() {
        User user = new User();

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFail() {
        User user = new User();

        user.setUsername("Eduard");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("Eduard");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertFalse(isUserCreated);
    }
}