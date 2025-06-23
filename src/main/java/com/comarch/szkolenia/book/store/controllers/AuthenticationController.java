package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.session.Cart;
import com.comarch.szkolenia.book.store.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final IUserDAO userDAO;

    @Resource
    private Cart cart;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userObject", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                            @RequestParam("password2") String password2) {
        if(!user.getPassword().equals(password2) || this.userDAO.getByLogin(user.getLogin()) != null) {
            return "redirect:/register";
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setRole(User.Role.USER);
        this.userDAO.persist(user);

        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = this.userDAO.getByLogin(login);
        if(user != null && DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        this.cart.getPositions().clear();
        return "redirect:/login";
    }
}
