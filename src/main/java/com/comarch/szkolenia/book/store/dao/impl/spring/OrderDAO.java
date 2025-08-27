package com.comarch.szkolenia.book.store.dao.impl.spring;

import com.comarch.szkolenia.book.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
}
