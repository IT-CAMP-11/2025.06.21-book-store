package com.comarch.szkolenia.book.store.filters;

import com.comarch.szkolenia.book.store.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            res.sendRedirect("/login");
        } else if(user.getRole() == User.Role.ADMIN) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/main");
        }
    }
}

