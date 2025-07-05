package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.exceptions.OrderValidationException;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.session.Cart;
import com.comarch.szkolenia.book.store.validators.OrderValidator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final IBookDAO bookDAO;
    private final IOrderDAO orderDAO;

    @Resource
    private Cart cart;

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("orderObject", new Order());
        return "order-form";
    }

    @PostMapping("/confirm")
    public String confirm(@ModelAttribute Order order, HttpSession session) {
        try {
            OrderValidator.validateOrder(order);
        } catch (OrderValidationException e) {
            return "redirect:/order";
        }
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setDate(new Date());
        boolean isIncorrectQuantity = false;
        for (Map.Entry<Integer, Integer> position : this.cart.getPositions().entrySet()) {
            Book book = this.bookDAO.getById(position.getKey());
            if (book != null && book.getQuantity() >= position.getValue()) {
                order.getPositions().add(new Order.Position(position.getKey(), position.getValue()));
            } else {
                position.setValue(book.getQuantity());
                isIncorrectQuantity = true;
            }
        }
        if (isIncorrectQuantity) {
            return "redirect:/cart";
        }
        order.setPrice(this.cart.calculatePrice());
        this.orderDAO.persist(order);
        for (Order.Position position : order.getPositions()) {
            Book book = this.bookDAO.getById(position.getBookId());
            book.setQuantity(book.getQuantity() - position.getQuantity());
        }
        this.cart.getPositions().clear();
        return "redirect:/main";
    }

    @GetMapping("/orders")
    public String orders(Model model, HttpSession session) {
        List<Order> orders = this.orderDAO.getByUserId(((User) session.getAttribute("user")).getId());

        List<com.comarch.szkolenia.book.store.model.view.Order> viewOrders = new ArrayList<>();
        for(Order order : orders) {
            com.comarch.szkolenia.book.store.model.view.Order viewOrder =
                    new com.comarch.szkolenia.book.store.model.view.Order();
            viewOrder.setId(order.getId());
            viewOrder.setDate(order.getDate());
            viewOrder.setPrice(order.getPrice());
            viewOrder.setNo(order.getNo());
            viewOrder.setCity(order.getCity());
            viewOrder.setStreet(order.getStreet());
            viewOrder.setPostCode(order.getPostCode());
            viewOrder.setPhoneNumber(order.getPhoneNumber());

            for(Order.Position position : order.getPositions()) {
                Book book = this.bookDAO.getById(position.getBookId());
                com.comarch.szkolenia.book.store.model.view.Order.Position viewPosition =
                        new com.comarch.szkolenia.book.store.model.view.Order.Position();
                viewPosition.setTitle(book.getTitle());
                viewPosition.setAuthor(book.getAuthor());
                viewPosition.setPrice(book.getPrice());
                viewPosition.setQuantity(position.getQuantity());

                viewOrder.getPositions().add(viewPosition);
            }

            viewOrders.add(viewOrder);
        }

        model.addAttribute("orders", viewOrders);

        return "orders";
    }
}
