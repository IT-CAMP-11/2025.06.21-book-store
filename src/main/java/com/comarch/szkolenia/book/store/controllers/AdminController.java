package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.exceptions.BookValidationException;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.services.IBookService;
import com.comarch.szkolenia.book.store.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final IBookService bookService;

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("bookModel", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        try {
            BookValidator.validateBook(book);
            this.bookService.merge(book);
            return "redirect:/main";
        } catch (BookValidationException e) {
            return "redirect:/addBook";
        }
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, final Model model) {
        Optional<Book> bookBox = this.bookService.getById(id);

        return bookBox
                .map(b -> {
                    model.addAttribute("bookModel", b);
                    return "addBook";
                })
                .orElse("redirect:/main");
    }

    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute Book book) {
        try {
            BookValidator.validateBook(book);
        } catch (BookValidationException e) {
            return "redirect:/editBook/" + id;
        }
        book.setId(id);
        this.bookService.merge(book);
        return "redirect:/main";
    }
}
