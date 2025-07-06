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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void confirmOrder(Order order) {
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setDate(new Date());
        order.setPrice(this.cart.calculatePrice());
        for(Map.Entry<Integer, Integer> position : this.cart.getPositions().entrySet()) {
            order.getPositions().add(new Order.Position(position.getKey(), position.getValue()));
            Book book = this.bookDAO.getById(position.getKey());
            book.setQuantity(book.getQuantity() - position.getValue());
        }
        this.orderDAO.persist(order);
        this.cart.getPositions().clear();
    }

    @Override
    public List<VOrder> getCurrentUserOrders() {
        List<Order> orders = this.orderDAO.getByUserId(((User) session.getAttribute("user")).getId());
        List<VOrder> result = new ArrayList<>();

        for(Order order : orders) {
            VOrder vOrder = new VOrder(order);
            for(Order.Position position : order.getPositions()) {
                Book book = this.bookDAO.getById(position.getBookId());
                VOrder.Position vPosition = new VOrder.Position(book, position.getQuantity());
                vOrder.getPositions().add(vPosition);
            }
            result.add(vOrder);
        }

        return result;
    }
}
