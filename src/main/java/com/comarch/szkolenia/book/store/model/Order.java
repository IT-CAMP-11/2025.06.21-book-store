package com.comarch.szkolenia.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String street;
    private String no;
    private String postCode;
    private String phoneNumber;
    private int userId;
    @Transient
    private final List<Position> positions = new ArrayList<>();
    private Date date;
    private double price;
}
