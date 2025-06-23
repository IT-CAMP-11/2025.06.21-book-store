package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final IBookDAO bookDAO;

    @GetMapping({"/main", "/", "/index"})
    public String main(Model model) {
        model.addAttribute("books", this.bookDAO.getAll());
        return "main";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
