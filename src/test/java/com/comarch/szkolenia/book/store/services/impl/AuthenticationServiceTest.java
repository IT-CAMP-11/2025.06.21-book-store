package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.exceptions.LoginAlreadyExistException;
import com.comarch.szkolenia.book.store.model.User;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AuthenticationServiceTest extends TestSuite {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    HttpSession httpSession;

    @Test
    public void registerTest() {
        User user = new User();
        user.setPassword("haslo123");
        user.setName("Janusz");
        user.setSurname("Kowalski");
        user.setAge(30);
        user.setLogin("janusz");
        String expectedPasswordHash = "1a7fcdd5a9fd433523268883cfded9d0";
        User.Role expectedRole = User.Role.USER;
        int expectedId = 1;

        Mockito.when(this.userDAO.saveAndReturnOptional(ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> {;
                    User u = invocation.getArgument(0);
                    u.setId(1);
                    return Optional.of(u);
                });

        this.authenticationService.register(user);

        Assertions.assertEquals(expectedPasswordHash, user.getPassword());
        Assertions.assertEquals(expectedRole, user.getRole());
        Assertions.assertEquals(expectedId, user.getId());

        Mockito.verify(this.userDAO, Mockito.times(1))
                .saveAndReturnOptional(ArgumentMatchers.any(User.class));
    }

    @Test
    public void registerDuplicateLoginTest() {
        User user = new User();
        user.setPassword("haslo123");
        user.setName("Janusz");
        user.setSurname("Kowalski");
        user.setAge(30);
        user.setLogin("takijuzjest");

        Mockito.when(this.userDAO.saveAndReturnOptional(ArgumentMatchers.any(User.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                LoginAlreadyExistException.class,
                () -> this.authenticationService.register(user));
    }

    @Test
    public void incorrectLoginTest() {
        String login = "zlylogin";
        String password = "zlehaslo";
        Mockito.when(this.userDAO.findByLogin("zlylogin")).thenReturn(Optional.empty());
        httpSession.removeAttribute("user");

        this.authenticationService.authenticate(login, password);

        Assertions.assertNull(this.httpSession.getAttribute("user"));
    }

    @Test
    public void incorrectPasswordLoginTest() {
        String login = "janusz";
        String password = "zlehaslo";
        User user = new User();
        user.setPassword("1a7fcdd5a9fd433523268883cfded9d0");
        Mockito.when(this.userDAO.findByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
        httpSession.removeAttribute("user");

        this.authenticationService.authenticate(login, password);

        Assertions.assertNull(this.httpSession.getAttribute("user"));
    }

    @Test
    public void successLoginTest() {
        String login = "janusz";
        String password = "janusz123";
        User expectedUser = new User();
        expectedUser.setLogin("janusz");
        expectedUser.setPassword("1e6f2ac43951a6721d3d26a379cc7f8b");
        Mockito.when(this.userDAO.findByLogin(Mockito.anyString()))
                .thenReturn(Optional.of(expectedUser));
        httpSession.removeAttribute("user");

        this.authenticationService.authenticate(login, password);

        Assertions.assertSame(expectedUser, this.httpSession.getAttribute("user"));
    }
}


