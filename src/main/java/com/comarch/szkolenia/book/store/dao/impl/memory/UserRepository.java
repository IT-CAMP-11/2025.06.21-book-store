package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.services.IIdSequence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserDAO {
    private final List<User> users = new ArrayList<>();
    private final IIdSequence idSequence;

    public UserRepository(IIdSequence idSequence) {
        this.idSequence = idSequence;
        this.users.add(new User(this.idSequence.getNextId(), "admin", "21232f297a57a5a743894a0e4a801fc3",
                "Pan", "Admin", 30, User.Role.ADMIN));

        this.users.add(new User(this.idSequence.getNextId(), "janusz", "1e6f2ac43951a6721d3d26a379cc7f8b",
                "Janusz", "Kowalski", 40, User.Role.USER));
    }

    @Override
    public void persist(User user) {
        user.setId(this.idSequence.getNextId());
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
