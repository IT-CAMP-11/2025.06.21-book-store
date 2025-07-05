package com.comarch.szkolenia.book.store.controllers;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
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
        Book byIsbn = bookDAO.findByIsbn(book.getIsbn());
        if(byIsbn == null) {
            bookDAO.persist(book);
        } else {
            byIsbn.setQuantity(byIsbn.getQuantity() + book.getQuantity());
        }

        return "redirect:/main";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book byId = this.bookDAO.getById(id);
        model.addAttribute("bookModel", byId);
        return "addBook";
    }

    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute Book book) {
        Book byId = this.bookDAO.getById(id);
        if(byId != null) {
            byId.setTitle(book.getTitle());
            byId.setAuthor(book.getAuthor());
            byId.setIsbn(book.getIsbn());
            byId.setPrice(book.getPrice());
            byId.setQuantity(book.getQuantity());
        }
        return "redirect:/main";
    }
}
