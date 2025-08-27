package com.comarch.szkolenia.book.store.controllers.rest;

import com.comarch.szkolenia.book.store.services.ICartService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("restCartController")
@RequiredArgsConstructor
@RequestMapping("/rest/api/v1/cart")
public class CartController {

    private final ICartService cartService;

    @Resource
    private Cart cart;

    @GetMapping("/{bookId}")
    public void addBook(@PathVariable int bookId) {
        this.cartService.add(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void removeFromCart(@PathVariable int bookId) {
        this.cart.removePosition(bookId);
    }
}
