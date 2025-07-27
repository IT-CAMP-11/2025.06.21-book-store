package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.model.Position;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.services.IOrderService;
import com.comarch.szkolenia.book.store.session.Cart;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IBookDAO bookDAO;
    private final IUserDAO userDAO;

    @Autowired
    HttpSession session;

    @Resource
    private Cart cart;

    @Override
    public void confirm(final Order order) {
        int userId = ((User) session.getAttribute("user")).getId();
        order.setDate(new Date());
        order.setPrice(this.cart.calculatePrice());
        this.cart.getPositions().forEach((bookId, quantity) -> {
            this.bookDAO.getById(bookId).ifPresent(book -> {
                        order.getPositions().add(new Position(book, quantity));
                        book.setQuantity(book.getQuantity() - quantity);
                    }
            );
        });
        Optional<User> userBox = this.userDAO.getById(userId);
        if (userBox.isPresent()) {
            User user = userBox.get();
            user.getOrders().add(order);
            this.userDAO.merge(user);
        }
        this.cart.getPositions().clear();
    }

    @Override
    public List<Order> getCurrentUserOrders() {
        int userId = ((User) session.getAttribute("user")).getId();
        Optional<User> userBox = this.userDAO.getById(userId);
        if (userBox.isPresent()) {
            return userBox.get().getOrders();
        }
        return new ArrayList<>();
    }
}
