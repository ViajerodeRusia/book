package com.library.book.controller;

import com.library.book.model.Book;
import com.library.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}") //GET https://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book foundBook = bookService.findBook(id);
        if(foundBook == null) {
            // 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundBook);
    }
    @PostMapping //POST https://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.creatBook(book);
    }

    @PutMapping //PUT https://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if(foundBook == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @GetMapping //GET https://localhost:8080/books
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("{id}") //DELETE https://localhost:8080/books
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
