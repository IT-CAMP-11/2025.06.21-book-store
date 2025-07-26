package com.comarch.szkolenia.book.store.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tposition")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bookId;
    private int quantity;
    private int orderId;

    public Position(int bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
