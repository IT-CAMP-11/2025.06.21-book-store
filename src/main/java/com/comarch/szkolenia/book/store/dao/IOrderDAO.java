package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void persist(Order order);
    Order getById(int id);
    List<Order> getAll();
    List<Order> getByUserId(int userId);
}
