package com.comarch.szkolenia.book.store.validators;

import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.exceptions.BookValidationException;

public class BookValidator {

    public static void validateTitle(String title) {
        String regex = ".+";
        if (title == null || !title.matches(regex)) {
            throw new BookValidationException("Title cannot be null or empty");
        }
    }

    public static void validateAuthor(String author) {
        String regex = ".+";
        if (author == null || !author.matches(regex)) {
            throw new BookValidationException("Author cannot be null or empty");
        }
    }

    public static void validateIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new BookValidationException("ISBN cannot be null or empty");
        }
        String regex = "^97[89][- ]?83([- ]?\\d+){3}[- ]?\\d$";
        if (!isbn.matches(regex)) {
            throw new BookValidationException("Invalid ISBN format for Poland (must be ISBN-13, start with 978-83 or 979-83)");
        }
    }

    public static void validatePrice(Double price) {
        if (price == null || price <= 0) {
            throw new BookValidationException("Price must be greater than zero");
        }
    }

    public static void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new BookValidationException("Quantity cannot be negative");
        }
    }

    public static void validateBook(Book book) {
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateIsbn(book.getIsbn());
        validatePrice(book.getPrice());
        validateQuantity(book.getQuantity());
    }
}
