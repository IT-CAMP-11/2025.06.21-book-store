package com.comarch.szkolenia.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    //@JsonIgnore
    private String password;
    private String name;
    private String surname;
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    @Transient
    @JsonProperty("orders")
    private String ordersRef;

    public enum Role {
        USER,
        ADMIN
    }

    public String getOrdersRef() {
        if(this.ordersRef == null) {
            this.ordersRef = "http://localhost:8080/rest/api/v1/order?userId=" + this.id;
        }
        return this.ordersRef;
    }
}
