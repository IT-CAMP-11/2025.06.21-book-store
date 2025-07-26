package com.comarch.szkolenia.book.store.dao.impl.hibernate;

import com.comarch.szkolenia.book.store.dao.IPositionDAO;
import com.comarch.szkolenia.book.store.model.Position;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PositionDAO implements IPositionDAO {
    private final SessionFactory sessionFactory;

    private final String GET_BY_ORDER_ID_HQL = "FROM com.comarch.szkolenia.book.store.model.Position WHERE orderId = :orderId";

    @Override
    public List<Position> getByOrderId(int orderId) {
        Session session = this.sessionFactory.openSession();
        Query<Position> query = session.createQuery(GET_BY_ORDER_ID_HQL, Position.class);
        query.setParameter("orderId", orderId);
        List<Position> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void persist(Position position) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
