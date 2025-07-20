package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.services.IIdSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements IOrderDAO {
    private final List<Order> orders = new ArrayList<>();
    private final IIdSequence idSequence;

    @Override
    public void persist(Order order) {
        order.setId(this.idSequence.getNextId());
        this.orders.add(order);
    }

    @Override
    public Optional<Order> getById(final int id) {
        return this.orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public List<Order> getByUserId(final int userId) {
        return this.orders.stream()
                .filter(o -> o.getUserId() == userId)
                .toList();
    }
}
