package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    Optional<Book> merge(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> searchByTitleOrAuthor(String searchTerm);
}
