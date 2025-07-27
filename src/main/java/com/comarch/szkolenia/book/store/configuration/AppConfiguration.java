package com.comarch.szkolenia.book.store.configuration;

import com.comarch.szkolenia.book.store.filters.AdminFilter;
import com.comarch.szkolenia.book.store.filters.LoginFilter;
import org.hibernate.SessionFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
