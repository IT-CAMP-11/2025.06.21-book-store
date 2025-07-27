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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Position> positions = new ArrayList<>();
    private Date date;
    private double price;

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
}

