package com.comarch.szkolenia.book.store;

import com.comarch.szkolenia.book.store.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
