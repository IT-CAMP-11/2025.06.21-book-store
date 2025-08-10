package com.comarch.szkolenia.book.store.dao.impl.spring;

import com.comarch.szkolenia.book.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    // Nadpisanie metody save aby zwracała Optional<User>
    default Optional<User> saveAndReturnOptional(User user) {
        try {
            User savedUser = save(user);
            return Optional.ofNullable(savedUser);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
