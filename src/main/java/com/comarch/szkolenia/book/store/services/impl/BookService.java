package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.dao.impl.spring.BookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final BookDAO bookDAO;

    @Override
    public Optional<Book> merge(final Book book) {
        return this.bookDAO.saveAndReturnOptional(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.findAll();
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String search) {
        return this.bookDAO.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search);
    }
}
