package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.User;

public interface IAuthenticationService {
    void register(User user);
    void authenticate(String login, String password);
    void logout();
}
