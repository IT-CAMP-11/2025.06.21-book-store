package com.comarch.szkolenia.book.store.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StringGenerator {

    //@Scheduled(fixedRate = 2000, initialDelay = 5000)
    @Scheduled(cron = "0,30,45 * * * * *")
    public void generateString() {
        System.out.println("jakas informacja");
        System.out.println(LocalDateTime.now());
    }
}
