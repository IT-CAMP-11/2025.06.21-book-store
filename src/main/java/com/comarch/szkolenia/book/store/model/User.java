package com.comarch.szkolenia.book.store.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private Role role;

    public enum Role {
        USER,
        ADMIN
    }
}
