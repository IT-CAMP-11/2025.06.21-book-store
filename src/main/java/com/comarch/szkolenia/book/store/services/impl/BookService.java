package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IBookService;
import com.comarch.szkolenia.book.store.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final IBookDAO bookDAO;

    @Override
    public void persistBook(final Book book) {
        this.bookDAO.findByIsbn(book.getIsbn())
                .ifPresentOrElse(
                        b -> b.setQuantity(b.getQuantity() + book.getQuantity()),
                        () -> {
                            BookValidator.validateBook(book);
                            bookDAO.persist(book);
                        }
                );
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        this.bookDAO.getById(id)
                .ifPresent(b -> {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setIsbn(book.getIsbn());
                    b.setPrice(book.getPrice());
                    b.setQuantity(book.getQuantity());
                }
        );
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
