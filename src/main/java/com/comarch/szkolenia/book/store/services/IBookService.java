package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    void persistBook(Book book);
    Optional<Book> getBookById(int id);
    void updateBook(int id, Book book);
    List<Book> getAll();
    List<Book> searchByTitleOrAuthor(String search);
}
