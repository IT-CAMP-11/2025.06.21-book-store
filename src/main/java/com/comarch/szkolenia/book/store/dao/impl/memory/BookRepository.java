package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository implements IBookDAO {
    private final List<Book> books = new ArrayList<>();
    private int lastId = 4;

    public BookRepository() {
        this.books.add(
                new Book(1, "Java. Kompendium programisty. Wydanie XII",
                "Herbert Schildt", "978-83-832-2156-4",
                129.35, 10));
        this.books.add(
                new Book(2, "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-80",
                83.85, 10));
        this.books.add(
                new Book(3, "Java. Rusz głową! Wydanie III",
                "Kathy Sierra, Bert Bates, Trisha Gee", "978-83-283-9984-6",
                96.85, 10));
        this.books.add(
                new Book(4, "Java. Teoria w praktyce",
                "Michał Suwała", "978-83-289-0022-6",
                70.85, 10));
    }

    @Override
    public Book getById(int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void persist(Book book) {
        book.setId(++this.lastId);
        this.books.add(book);
    }

    public Book findByIsbn(String isbn) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
