package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final IBookDAO bookDAO;

    @Override
    public Optional<Book> merge(final Book book) {
        return this.bookDAO.merge(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
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
