package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.exceptions.InvalidCartException;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.model.view.CartPosition;
import com.comarch.szkolenia.book.store.services.ICartService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final IBookDAO bookDAO;

    @Resource
    private Cart cart;

    @Override
    public List<CartPosition> getCartPositions() {
        final List<CartPosition> cartPositions = new ArrayList<>();

        for (Map.Entry<Integer, Integer> position : this.cart.getPositions().entrySet()) {
            this.bookDAO.getById(position.getKey())
                    .ifPresent(b -> {
                        cartPositions.add(
                                new CartPosition(b.getId(), b.getTitle(), b.getAuthor(),
                                        position.getValue(), b.getPrice())
                        );
                    });
        }

        return cartPositions;
    }

    @Override
    public void addToCart(int bookId) {
        if (this.bookDAO.getById(bookId).isPresent()) {
            Integer quantity = this.cart.getPositions().get(bookId);
            if (quantity == null) {
                this.cart.getPositions().put(bookId, 1);
            } else {
                this.cart.getPositions().put(bookId, quantity + 1);
            }
        }
    }

    @Override
    public void validateCart() {
        boolean isValid = true;
        for (Map.Entry<Integer, Integer> position : this.cart.getPositions().entrySet()) {
            Optional<Book> bookBox = this.bookDAO.getById(position.getKey());
            if (bookBox.isEmpty()) {
                this.cart.removePosition(position.getKey());
                isValid = false;
            } else if (bookBox.get().getQuantity() < position.getValue()) {
                position.setValue(bookBox.get().getQuantity());
                isValid = false;
            }
        }
        if (!isValid) {
            throw new InvalidCartException();
        }
    }
}
