package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.exceptions.LoginAlreadyExistException;
import com.comarch.szkolenia.book.store.exceptions.UserValidationException;
import com.comarch.szkolenia.book.store.services.IAuthenticationService;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.validators.UserValidator;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userObject", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                            @RequestParam("password2") String password2) {
        try {
            UserValidator.validateUser(user);
            UserValidator.checkPasswordsMatch(user.getPassword(), password2);
            this.authenticationService.register(user);
        } catch (UserValidationException | LoginAlreadyExistException e) {
            return "redirect:/register";
        }

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
        try {
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
        } catch (UserValidationException e) {
            return "redirect:/login";
        }

        this.authenticationService.authenticate(login, password);
        if(session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/login";
    }
}
