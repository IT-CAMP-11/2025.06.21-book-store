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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Book book;
    private int quantity;

    public Position(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}
