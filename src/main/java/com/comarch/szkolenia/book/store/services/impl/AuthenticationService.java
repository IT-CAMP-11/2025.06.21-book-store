package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.exceptions.LoginAlreadyExistException;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.services.IAuthenticationService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserDAO userDAO;

    @Autowired
    private HttpSession session;

    @Resource
    private Cart cart;

    @Override
    public void register(User user) {
        if(this.userDAO.getByLogin(user.getLogin()) != null) {
            throw new LoginAlreadyExistException("Login already exists: " + user.getLogin());
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setRole(User.Role.USER);
        this.userDAO.persist(user);
    }

    @Override
    public void authenticate(String login, String password) {
        User user = this.userDAO.getByLogin(login);
        if(user != null && DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            session.setAttribute("user", user);
        }
    }

    @Override
    public void logout() {
        session.removeAttribute("user");
        this.cart.getPositions().clear();
    }
}
