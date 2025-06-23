package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.User;

import java.util.List;

public interface IUserDAO {
    void persist(User user);
    User getById(int id);
    User getByLogin(String login);
    List<User> getAll();
}
