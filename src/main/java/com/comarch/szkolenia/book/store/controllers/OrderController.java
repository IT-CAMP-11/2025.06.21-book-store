package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.exceptions.InvalidCartException;
import com.comarch.szkolenia.book.store.exceptions.OrderValidationException;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.services.ICartService;
import com.comarch.szkolenia.book.store.services.IOrderService;
import com.comarch.szkolenia.book.store.validators.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ICartService cartService;
    private final IOrderService orderService;

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("orderObject", new Order());
        return "order-form";
    }

    @PostMapping("/confirm")
    public String confirm(@ModelAttribute Order order) {
        try {
            OrderValidator.validateOrder(order);
            this.cartService.validate();
        } catch (OrderValidationException e) {
            return "redirect:/order";
        } catch (InvalidCartException e) {
            return "redirect:/cart";
        }

        this.orderService.confirm(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", this.orderService.getCurrentUserOrders());
        return "orders";
    }
}
