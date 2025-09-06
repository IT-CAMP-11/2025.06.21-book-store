package com.comarch.szkolenia.book.store.thymeleaf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CustomDateFormatterTest {

    @Test
    public void formatTest() {
        LocalDateTime dateTime = LocalDateTime.of(
                2024,
                6,
                20,
                15,
                30,
                45);
        CustomDateFormatter formatter = new CustomDateFormatter();
        String expected = "20.06.2024 15:30:45";

        String actual = formatter.format(dateTime);

        Assertions.assertEquals(expected, actual);
    }
}
