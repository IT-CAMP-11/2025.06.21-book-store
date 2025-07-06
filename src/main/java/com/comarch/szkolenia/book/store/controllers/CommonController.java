package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final IBookService bookService;

    @GetMapping({"/main", "/", "/index"})
    public String main(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("books", this.bookService.searchByTitleOrAuthor(search));
        } else {
            model.addAttribute("books", this.bookService.getAll());
        }
        model.addAttribute("search", search != null ? search : "");
        return "main";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
