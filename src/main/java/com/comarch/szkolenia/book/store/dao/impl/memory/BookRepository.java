package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IIdSequence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements IBookDAO {
    private final List<Book> books = new ArrayList<>();
    private final IIdSequence idSequence;

    public BookRepository(IIdSequence idSequence) {
        this.idSequence = idSequence;
        this.books.add(
                new Book(this.idSequence.getNextId(), "Java. Kompendium programisty. Wydanie XII",
                "Herbert Schildt", "978-83-832-2156-4",
                129.35, 10));
        this.books.add(
                new Book(this.idSequence.getNextId(), "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-80",
                83.85, 10));
        this.books.add(
                new Book(this.idSequence.getNextId(), "Java. Rusz głową! Wydanie III",
                "Kathy Sierra, Bert Bates, Trisha Gee", "978-83-283-9984-6",
                96.85, 10));
        this.books.add(
                new Book(this.idSequence.getNextId(), "Java. Teoria w praktyce",
                "Michał Suwała", "978-83-289-0022-6",
                70.85, 10));
    }

    @Override
    public Optional<Book> getById(int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void persist(Book book) {
        book.setId(this.idSequence.getNextId());
        this.books.add(book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll();
        }
        List<Book> result = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();
        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(lowerSearchTerm)
                    || book.getAuthor().toLowerCase().contains(lowerSearchTerm)) {
                result.add(book);
            }
        }
        return result;
    }
}
