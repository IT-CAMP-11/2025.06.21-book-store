package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.services.ICartService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @Resource
    private Cart cart;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", this.cartService.getPositions());
        model.addAttribute("cartSum", this.cart.calculatePrice());
        return "cart";
    }
}
