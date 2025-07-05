package com.comarch.szkolenia.book.store.exceptions;

public class BookValidationException extends RuntimeException {

    public BookValidationException(String message) {
        super(message);
    }
}
