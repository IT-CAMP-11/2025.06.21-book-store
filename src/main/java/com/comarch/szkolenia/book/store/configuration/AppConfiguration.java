package com.comarch.szkolenia.book.store.configuration;

import com.comarch.szkolenia.book.store.filters.AdminFilter;
import com.comarch.szkolenia.book.store.filters.LoginFilter;
import org.hibernate.SessionFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.comarch.szkolenia.book.store")
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<AdminFilter> adminRegistrationBean(AdminFilter adminFilter) {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/addBook", "/editBook/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginRegistrationBean(LoginFilter loginFilter) {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(loginFilter);
        registrationBean.addUrlPatterns(
            "/login",
            "/cart",
            "/cart/*",
            "/order",
            "/confirm"
        );
        return registrationBean;
    }

    /*@Bean
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/book_store",
                    "root",
                    "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Problem z polaczeniem do bazy !!");
            e.printStackTrace();
        }

        return null;
    }*/

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
