package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.Book;

import java.util.List;

public interface IBookService {
    void persistBook(Book book);
    Book getBookById(int id);
    void updateBook(int id, Book book);
    List<Book> getAll();
    List<Book> searchByTitleOrAuthor(String search);
}
