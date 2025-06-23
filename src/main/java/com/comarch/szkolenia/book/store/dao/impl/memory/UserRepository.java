package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository implements IUserDAO {
    private final List<User> users = new ArrayList<>();
    private int lastId = 2;

    public UserRepository() {
        this.users.add(new User(1, "admin", "21232f297a57a5a743894a0e4a801fc3",
                "Pan", "Admin", 30, User.Role.ADMIN));

        this.users.add(new User(2, "janusz", "1e6f2ac43951a6721d3d26a379cc7f8b",
                "Janusz", "Kowalski", 40, User.Role.USER));
    }

    @Override
    public void persist(User user) {
        user.setId(++this.lastId);
        this.users.add(user);
    }

    @Override
    public User getById(int id) {
        for(User user : this.users) {
            if(user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }
}
