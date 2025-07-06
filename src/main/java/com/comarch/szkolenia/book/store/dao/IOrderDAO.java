package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void persist(Order order);
    Optional<Order> getById(int id);
    List<Order> getAll();
    List<Order> getByUserId(int userId);
}
