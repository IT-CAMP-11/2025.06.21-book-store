package com.comarch.szkolenia.book.store.model.view;

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
public class Order {
    private int id;
    private Date date;
    private double price;
    private String city;
    private String street;
    private String no;
    private String postCode;
    private String phoneNumber;
    private final List<Position> positions = new ArrayList<>();

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

        public double sum() {
            return this.quantity * this.price;
        }
    }
}
