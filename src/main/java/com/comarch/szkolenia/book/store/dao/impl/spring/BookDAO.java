package com.comarch.szkolenia.book.store.dao.impl.spring;

import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

    // getById(int id) - dziedziczone z JpaRepository jako findById(Integer id)
    // getAll() - dziedziczone z JpaRepository jako findAll()
    // merge(Book book) - dziedziczone z JpaRepository jako save(Book book)

    // Nadpisanie metody save aby zwracała Optional<User>
    default Optional<Book> saveAndReturnOptional(Book book) {
        try {
            Book savedBook = save(book);
            return Optional.ofNullable(savedBook);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM tbook b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    //List<Book> findByTitleIgnoreCaseLikeOrAuthorIgnoreCaseLike(String title, String author);
}
