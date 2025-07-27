package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> merge(User user);
    Optional<User> getById(int id);
    Optional<User> getByLogin(String login);
    List<User> getAll();
}
