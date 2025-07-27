package com.comarch.szkolenia.book.store.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String password;
    private String name;
    private String surname;
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Role role;

    public enum Role {
        USER,
        ADMIN
    }
}
