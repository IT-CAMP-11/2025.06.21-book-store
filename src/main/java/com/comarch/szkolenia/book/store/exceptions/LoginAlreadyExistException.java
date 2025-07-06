package com.comarch.szkolenia.book.store.exceptions;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
