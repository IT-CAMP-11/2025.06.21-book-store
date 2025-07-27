package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> merge(Book book);
    Optional<Book> getById(int id);
    List<Book> getAll();
    List<Book> searchByTitleOrAuthor(String search);
}
