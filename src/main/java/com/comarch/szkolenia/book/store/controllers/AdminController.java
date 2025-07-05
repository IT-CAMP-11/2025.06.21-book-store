package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.exceptions.BookValidationException;
import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final IBookDAO bookDAO;

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("bookModel", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        try {
            BookValidator.validateIsbn(book.getIsbn());
            BookValidator.validateQuantity(book.getQuantity());

            Book byIsbn = bookDAO.findByIsbn(book.getIsbn());
            if (byIsbn == null) {
                BookValidator.validateBook(book);
                bookDAO.persist(book);
            } else {
                byIsbn.setQuantity(byIsbn.getQuantity() + book.getQuantity());
            }

            return "redirect:/main";

        } catch (BookValidationException e) {
            return "redirect:/addBook";
        }
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book byId = this.bookDAO.getById(id);
        model.addAttribute("bookModel", byId);
        return "addBook";
    }

    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute Book book) {
        try {
            BookValidator.validateBook(book);
        } catch (BookValidationException e) {
            return "redirect:/editBook/" + id;
        }

        Book byId = this.bookDAO.getById(id);
        if (byId != null) {
            byId.setTitle(book.getTitle());
            byId.setAuthor(book.getAuthor());
            byId.setIsbn(book.getIsbn());
            byId.setPrice(book.getPrice());
            byId.setQuantity(book.getQuantity());
        }
        return "redirect:/main";
    }
}
