package com.comarch.szkolenia.book.store.configuration;

import com.comarch.szkolenia.book.store.filters.AdminFilter;
import com.comarch.szkolenia.book.store.filters.LoginFilter;
import com.comarch.szkolenia.book.store.thymeleaf.CustomDateFormatter;
import org.hibernate.SessionFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

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

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine, CustomDateFormatter customDateFormatter) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setStaticVariables(Map.of("customDateFormatter", customDateFormatter));
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
