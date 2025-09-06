package com.comarch.szkolenia.book.store.validators;

import com.comarch.szkolenia.book.store.exceptions.BookValidationException;
import com.comarch.szkolenia.book.store.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookValidatorTest {

    @Test
    public void validateNullTitleTest() {
        String title = null;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateTitle(title));
    }

    @Test
    public void emptyTitleTest() {
        String title = "";

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateTitle(title));
    }

    @Test
    public void correctTitleTest() {
        String title = "Correct Title";

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validateTitle(title));
    }

    @Test
    public void validateNullAuthorTest() {
        String author = null;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void emptyAuthorTest() {
        String author = "";

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void correctAuthorTest() {
        String author = "Correct Author";

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void validateNullIsbnTest() {
        String isbn = null;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateIsbn(isbn));
    }

    @Test
    public void emptyIsbnTest() {
        String isbn = "";

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateIsbn(isbn));
    }

    @Test
    public void invalidIsbnTest() {
        String isbn = "1234567890123";

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateIsbn(isbn));
    }


    @Test
    public void correctIsbnTest() {
        String isbn = "978-83-12345-67-8";

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validateIsbn(isbn));
    }

    @Test
    public void validateNullPriceTest() {
        Double price = null;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validatePrice(price));
    }

    @Test
    public void zeroPriceTest() {
        Double price = 0.0;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validatePrice(price));
    }

    @Test
    public void negativePriceTest() {
        Double price = -10.0;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validatePrice(price));
    }

    @Test
    public void correctPriceTest() {
        Double price = 29.99;

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validatePrice(price));
    }

    @Test
    public void validateNullQuantityTest() {
        Integer quantity = null;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateQuantity(quantity));
    }

    @Test
    public void negativeQuantityTest() {
        Integer quantity = -5;

        Assertions.assertThrows(
                BookValidationException.class,
                () -> BookValidator.validateQuantity(quantity));
    }

    @Test
    public void correctQuantityTest() {
        Integer quantity = 10;

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validateQuantity(quantity));
    }

    @Test
    public void validateCorrectBookTest() {
        Book book = new Book(
                        1,
                        "Java. Kompendium programisty. Wydanie XII",
                        "Herbert Schildt",
                        "978-83-832-2156-4",
                        129.35,
                        10
                );

        Assertions.assertDoesNotThrow(
                () -> BookValidator.validateBook(book));
    }
}
