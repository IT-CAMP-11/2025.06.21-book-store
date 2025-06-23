package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.session.Cart;
import com.comarch.szkolenia.book.store.model.view.CartPosition;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final IBookDAO bookDAO;

    @Resource
    private Cart cart;

    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartPosition> cartPositions = new ArrayList<>();

        for(Map.Entry<Integer, Integer> position : this.cart.getPositions().entrySet()) {
            Book book = this.bookDAO.getById(position.getKey());
            if(book != null) {
                cartPositions.add(
                        new CartPosition(book.getTitle(), book.getAuthor(),
                                position.getValue(), book.getPrice())
                );
            }
        }

        model.addAttribute("cart", cartPositions);
        model.addAttribute("cartSum", this.cart.calculatePrice());
        return "cart";
    }

    @GetMapping("/cart/{bookId}")
    public String addBook(@PathVariable int bookId) {
        if(this.bookDAO.getById(bookId) != null) {
            Integer quantity = this.cart.getPositions().get(bookId);
            if(quantity == null) {
                this.cart.getPositions().put(bookId, 1);
            } else {
                this.cart.getPositions().put(bookId, quantity + 1);
            }
        }
        return "redirect:/main";
    }
}
