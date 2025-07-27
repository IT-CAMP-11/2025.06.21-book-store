package com.comarch.szkolenia.book.store.dao.impl.hibernate;

import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.model.Order;
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
public class OrderDAO implements IOrderDAO {
    private final SessionFactory sessionFactory;

    private final String GET_BY_ID_HQL = "FROM com.comarch.szkolenia.book.store.model.Order WHERE id = :id";
    private final String GET_ALL_HQL = "FROM com.comarch.szkolenia.book.store.model.Order";
    private final String GET_BY_USER_ID_HQL = "FROM com.comarch.szkolenia.book.store.model.Order WHERE userId = :userId";

    @Override
    public void persist(Order order) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Order> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(GET_BY_ID_HQL, Order.class);
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
    public List<Order> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(GET_ALL_HQL, Order.class);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Order> getByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(GET_BY_USER_ID_HQL, Order.class);
        query.setParameter("userId", userId);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }
}
