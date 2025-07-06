package com.comarch.szkolenia.book.store.model.view;

import com.comarch.szkolenia.book.store.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VOrder {
    private int id;
    private Date date;
    private double price;
    private String city;
    private String street;
    private String no;
    private String postCode;
    private String phoneNumber;
    private final List<Position> positions = new ArrayList<>();

    public VOrder(com.comarch.szkolenia.book.store.model.Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.price = order.getPrice();
        this.city = order.getCity();
        this.street = order.getStreet();
        this.no = order.getNo();
        this.postCode = order.getPostCode();
        this.phoneNumber = order.getPhoneNumber();
    }

    public String addressLine() {
        return new StringBuilder()
                .append("Adres: ")
                .append("ul. ")
                .append(this.street)
                .append(" ")
                .append(this.no)
                .append(", ")
                .append(this.city)
                .append(" ")
                .append(this.postCode)
                .append(" tel. ")
                .append(this.phoneNumber)
                .toString();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Position {
        private String title;
        private String author;
        private double price;
        private int quantity;

        public Position(Book book, int quantity) {
            this.title = book.getTitle();
            this.author = book.getAuthor();
            this.price = book.getPrice();
            this.quantity = quantity;
        }

        public double sum() {
            return this.quantity * this.price;
        }
    }
}
