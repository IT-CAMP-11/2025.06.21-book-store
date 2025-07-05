package com.comarch.szkolenia.book.store.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private Double price;
    private Integer quantity;
}
