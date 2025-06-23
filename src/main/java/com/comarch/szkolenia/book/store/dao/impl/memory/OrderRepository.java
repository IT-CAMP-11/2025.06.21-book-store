package com.comarch.szkolenia.book.store.dao.impl.memory;

import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository implements IOrderDAO {
    private final List<Order> orders = new ArrayList<>();
    private int lastId = 0;

    @Override
    public void persist(Order order) {
        order.setId(++this.lastId);
        this.orders.add(order);
    }

    @Override
    public Order getById(int id) {
        for (Order order : this.orders) {
            if(order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public List<Order> getByUserId(int userId) {
        List<Order> result = new ArrayList<>();

        for (Order order : this.orders) {
            if(order.getUserId() == userId) {
                result.add(order);
            }
        }

        return result;
    }
}
