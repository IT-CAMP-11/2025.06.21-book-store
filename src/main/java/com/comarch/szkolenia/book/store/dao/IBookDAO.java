package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.Book;

import java.util.List;

public interface IBookDAO {
    Book getById(int id);
    List<Book> getAll();
    void persist(Book book);
}
