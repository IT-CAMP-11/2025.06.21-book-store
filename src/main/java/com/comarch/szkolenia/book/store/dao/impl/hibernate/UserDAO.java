package com.comarch.szkolenia.book.store.dao.impl.hibernate;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.model.User;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO implements IUserDAO {
    private final SessionFactory sessionFactory;

    private final String GET_BY_ID_HQL = "FROM com.comarch.szkolenia.book.store.model.User WHERE id = :id";
    private final String GET_BY_LOGIN_HQL = "FROM com.comarch.szkolenia.book.store.model.User WHERE login = :login";
    private final String GET_ALL_HQL = "FROM com.comarch.szkolenia.book.store.model.User";

    @Override
    public Optional<User> merge(User user) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();

            user.getOrders().forEach(order -> {
                order.getPositions().forEach(position -> {
                    position.setBook(session.merge(position.getBook()));
                });
            });

            User actual = session.merge(user);
            session.getTransaction().commit();
            return Optional.of(actual);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_BY_ID_HQL, User.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_BY_LOGIN_HQL, User.class);
        query.setParameter("login", login);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_ALL_HQL, User.class);
        List<User> result = query.getResultList();
        session.close();
        return result;
    }
}
