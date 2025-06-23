package com.comarch.szkolenia.book.store.session;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@SessionScope
@RequiredArgsConstructor
public class Cart {
    @Getter
    private final Map<Integer, Integer> positions = new HashMap<>();
    private final IBookDAO bookDAO;

    public double calculatePrice() {
        Set<Map.Entry<Integer, Integer>> entries = this.positions.entrySet();
        double result = 0.0;
        for(Map.Entry<Integer, Integer> position : entries) {
            int bookId = position.getKey();
            int quantity = position.getValue();

            Book book = this.bookDAO.getById(bookId);
            if(book != null) {
                result += book.getPrice() * quantity;
            }
        }

        return result;
    }
}
