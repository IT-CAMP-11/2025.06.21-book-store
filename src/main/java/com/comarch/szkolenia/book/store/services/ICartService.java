package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.view.CartPosition;

import java.util.List;

public interface ICartService {
    List<CartPosition> getCartPositions();
    void addToCart(int bookId);
    void validateCart();
}
