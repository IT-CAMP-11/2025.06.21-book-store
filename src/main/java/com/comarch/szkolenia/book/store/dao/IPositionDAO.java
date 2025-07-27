package com.comarch.szkolenia.book.store.dao;

import com.comarch.szkolenia.book.store.model.Position;

import java.util.List;

public interface IPositionDAO {
    List<Position> getByOrderId(int orderId);
    void persist(Position position);
}
