package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.configuration.TestAppConfiguration;
import com.comarch.szkolenia.book.store.dao.impl.spring.BookDAO;
import com.comarch.szkolenia.book.store.dao.impl.spring.OrderDAO;
import com.comarch.szkolenia.book.store.dao.impl.spring.UserDAO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestAppConfiguration.class})
@WebAppConfiguration
//@TestPropertySource("classpath:application-test.properties")
//@ActiveProfiles("test")
public class TestSuite {
    @MockitoBean
    UserDAO userDAO;

    @MockitoBean
    BookDAO bookDAO;

    @MockitoBean
    OrderDAO orderDAO;
}
