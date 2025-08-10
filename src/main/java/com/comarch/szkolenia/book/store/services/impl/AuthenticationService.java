package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.dao.impl.spring.UserDAO;
import com.comarch.szkolenia.book.store.exceptions.LoginAlreadyExistException;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.services.IAuthenticationService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final UserDAO userDAO;

    @Autowired
    private HttpSession session;

    @Resource
    private Cart cart;

    @Override
    public void register(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setRole(User.Role.USER);
        this.userDAO.saveAndReturnOptional(user)
                .orElseThrow(() ->
                        new LoginAlreadyExistException("Login already exists: " +
                                user.getLogin()));
    }

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDAO.findByLogin(login);
        if(userBox.isPresent() &&
                DigestUtils.md5DigestAsHex(password.getBytes()).equals(userBox.get().getPassword())) {
            session.setAttribute("user", userBox.get());
        }
    }

    @Override
    public void logout() {
        session.removeAttribute("user");
        this.cart.getPositions().clear();
    }
}
