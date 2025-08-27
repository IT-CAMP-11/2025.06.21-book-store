package com.comarch.szkolenia.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JsonIgnore
    private final List<Position> positions = new ArrayList<>();
    @Transient
    @JsonProperty("positions")
    private String positionsRef;
    private LocalDateTime date;
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

    public String getPositionsRef() {
        if(this.positionsRef == null) {
            this.positionsRef = "http://localhost:8080/rest/api/v1/position?orderId=" + this.id;
        }
        return this.positionsRef;
    }
}

