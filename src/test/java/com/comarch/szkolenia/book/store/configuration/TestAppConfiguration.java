package com.comarch.szkolenia.book.store.configuration;

import com.comarch.szkolenia.book.store.filters.AdminFilter;
import com.comarch.szkolenia.book.store.filters.LoginFilter;
import com.comarch.szkolenia.book.store.thymeleaf.CustomDateFormatter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Map;

@Configuration
@ComponentScan({"com.comarch.szkolenia.book.store.controllers",
        "com.comarch.szkolenia.book.store.services",
        "com.comarch.szkolenia.book.store.filters",
        "com.comarch.szkolenia.book.store.thymeleaf",
        "com.comarch.szkolenia.book.store.session"})
public class TestAppConfiguration {
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
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ClassLoaderTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine, CustomDateFormatter customDateFormatter) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setStaticVariables(Map.of("customDateFormatter", customDateFormatter));
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /*@Bean
    public BookDAO bookDAO() {
        return Mockito.mock(BookDAO.class);
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAOStub();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOStub();
    }*/
}
