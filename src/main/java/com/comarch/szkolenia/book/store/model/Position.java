package com.comarch.szkolenia.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Book book;
    @Transient
    @JsonProperty("book")
    private String bookRef;
    private int quantity;

    public Position(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public String getBookRef() {
        if(this.bookRef == null) {
            this.bookRef = "http://localhost:8080/rest/api/v1/book/" + this.book.getId();
        }
        return this.bookRef;
    }
}
