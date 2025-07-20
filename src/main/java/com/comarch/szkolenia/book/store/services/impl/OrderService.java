package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.model.User;
import com.comarch.szkolenia.book.store.model.view.VOrder;
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
    private final IOrderDAO orderDAO;
    private final IBookDAO bookDAO;

    @Autowired
    HttpSession session;

    @Resource
    private Cart cart;

    @Override
    public void confirmOrder(final Order order) {
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setDate(new Date());
        order.setPrice(this.cart.calculatePrice());
        this.cart.getPositions().forEach((bookId, quantity) -> {
            order.getPositions().add(new Order.Position(bookId, quantity));
            this.bookDAO.getById(bookId)
                    .ifPresent(book -> book.setQuantity(book.getQuantity() - quantity));
        });
        this.orderDAO.persist(order);
        this.cart.getPositions().clear();
    }

    @Override
    public List<VOrder> getCurrentUserOrders() {
        int userId = ((User) session.getAttribute("user")).getId();
        return this.orderDAO.getByUserId(userId).stream()
                .map(this::convertToVOrder)
                .toList();
    }

    private VOrder convertToVOrder(Order order) {
        final VOrder vOrder = new VOrder(order);
        order.getPositions().stream()
                .map(this::convertToVPosition)
                .filter(Objects::nonNull)
                .forEach(position -> vOrder.getPositions().add(position));
        return vOrder;
    }

    private VOrder.Position convertToVPosition(Order.Position position) {
        Optional<Book> bookBox = this.bookDAO.getById(position.getBookId());
        return bookBox
                .map(book -> new VOrder.Position(book, position.getQuantity()))
                .orElse(null);
    }
}
