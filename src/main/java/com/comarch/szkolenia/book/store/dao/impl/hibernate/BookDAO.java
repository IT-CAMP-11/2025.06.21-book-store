package com.comarch.szkolenia.book.store.dao.impl.hibernate;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
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
public class BookDAO implements IBookDAO {
    private final SessionFactory sessionFactory;

    private final String GET_BY_ID_HQL = "FROM com.comarch.szkolenia.book.store.model.Book WHERE id = :id";
    private final String GET_ALL_HQL = "FROM com.comarch.szkolenia.book.store.model.Book";
    private final String GET_BY_ISBN_HQL = "FROM com.comarch.szkolenia.book.store.model.Book WHERE isbn = :isbn";
    private final String SEARCH_BY_TITLE_OR_AUTHOR_HQL =
            "FROM com.comarch.szkolenia.book.store.model.Book WHERE title LIKE :pattern OR author LIKE :pattern";

    @Override
    public Optional<Book> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ID_HQL, Book.class);
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
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL_HQL, Book.class);
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Book> merge(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            Book actual = session.merge(book);
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
    public Optional<Book> findByIsbn(String isbn) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ISBN_HQL, Book.class);
        query.setParameter("isbn", isbn);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String searchTerm) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(SEARCH_BY_TITLE_OR_AUTHOR_HQL, Book.class);
        query.setParameter("pattern", "%" + searchTerm + "%");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }
}
