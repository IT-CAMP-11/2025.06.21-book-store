package com.comarch.szkolenia.book.store.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartPosition {
    private int bookId;
    private String title;
    private String author;
    private int quantity;
    private double price;

    public double getSum() {
        return quantity * price;
    }
}
