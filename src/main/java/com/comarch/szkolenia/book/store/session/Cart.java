package com.comarch.szkolenia.book.store.session;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@SessionScope
@RequiredArgsConstructor
public class Cart {
    @Getter
    private final Map<Integer, Integer> positions = new HashMap<>();
    private final IBookDAO bookDAO;

    public double calculatePrice() {
        return this.positions.entrySet().stream()
                .mapToDouble(p -> this.bookDAO.getById(p.getKey())
                        .map(book -> book.getPrice() * p.getValue()).orElse(0.0)
                )
                .sum();
    }

    public void removePosition(int bookId) {
        this.positions.remove(bookId);
    }
}
