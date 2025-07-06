package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IBookService;
import com.comarch.szkolenia.book.store.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final IBookDAO bookDAO;

    @Override
    public void persistBook(Book book) {
        Book bookWithIsbn = bookDAO.findByIsbn(book.getIsbn());
        if (bookWithIsbn == null) {
            BookValidator.validateBook(book);
            bookDAO.persist(book);
        } else {
            bookWithIsbn.setQuantity(bookWithIsbn.getQuantity() + book.getQuantity());
        }
    }

    @Override
    public Book getBookById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        Book bookFromDb = this.bookDAO.getById(id);
        if (bookFromDb != null) {
            bookFromDb.setTitle(book.getTitle());
            bookFromDb.setAuthor(book.getAuthor());
            bookFromDb.setIsbn(book.getIsbn());
            bookFromDb.setPrice(book.getPrice());
            bookFromDb.setQuantity(book.getQuantity());
        }
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String search) {
        return this.bookDAO.searchByTitleOrAuthor(search);
    }
}
