package com.comarch.szkolenia.book.store.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private int id;
    private String city;
    private String street;
    private String no;
    private String postCode;
    private String phoneNumber;
    private int userId;
    private final List<Position> positions = new ArrayList<>();
    private Date date;
    private double price;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Position {
        private int id;
        private int bookId;
        private int quantity;
        private int orderId;

        public Position(int bookId, int quantity) {
            this.bookId = bookId;
            this.quantity = quantity;
        }
    }
}
