package com.comarch.szkolenia.book.store.controllers.rest;

import com.comarch.szkolenia.book.store.dao.impl.spring.UserDAO;
import com.comarch.szkolenia.book.store.exceptions.LoginAlreadyExistException;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.services.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDAO userDAO;
    private final AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return this.userDAO.findById(id)
                .map(user -> {
                    user.setPassword(null);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("")
    public ResponseEntity<User> getByLogin(@RequestParam("login") String login) {
        return this.userDAO.findByLogin(login)
                .map(user -> {
                    user.setPassword(null);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            user.setId(0);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.authenticationService.register(user));
        } catch (LoginAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
