package com.comarch.szkolenia.book.store.services;

import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.model.view.VOrder;

import java.util.List;

public interface IOrderService {
    void confirmOrder(Order order);
    List<VOrder> getCurrentUserOrders();
}
