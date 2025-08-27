package com.comarch.szkolenia.book.store.controllers.rest;

import com.comarch.szkolenia.book.store.model.Book;
import com.comarch.szkolenia.book.store.model.dto.ListResponseDTO;
import com.comarch.szkolenia.book.store.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable("id") int id) {
        if(id == 0) {
            return ResponseEntity.badRequest().build();
        }
        book.setId(id);
        return bookService.merge(book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        book.setId(0);
        return this.bookService.merge(book)
                .map(b -> ResponseEntity.status(HttpStatus.CREATED).body(b))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") int id) {
        return this.bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("")
    public ListResponseDTO<Book> get(@RequestParam(name = "pattern", required = false) String pattern) {
        if (pattern == null) {
            return new ListResponseDTO<>(this.bookService.getAll());
        } else {
            return new ListResponseDTO<>(this.bookService.searchByTitleOrAuthor(pattern));
        }
    }
}
