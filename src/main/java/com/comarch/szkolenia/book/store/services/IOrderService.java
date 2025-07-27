package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.Order;

import java.util.List;

public interface IOrderService {
    void confirm(Order order);
    List<Order> getCurrentUserOrders();
}
