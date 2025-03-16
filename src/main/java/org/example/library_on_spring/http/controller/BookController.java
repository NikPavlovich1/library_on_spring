package org.example.library_on_spring.http.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.dto.BookCreateEditDto;
import org.example.library_on_spring.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Validated BookCreateEditDto bookDto) {
        Book createdBook = bookService.save(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Validated BookCreateEditDto bookUpdateDto) {
        Book updatedBook = bookService.update(id, bookUpdateDto);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> findByCategory(@RequestParam String category) {
        if (category == null) {
            List<Book> books = bookService.findAll();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        List<Book> books = bookService.findByCategory(category);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/filter/title")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        List<Book> books = bookService.findByTitle(title);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/filter/author")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
        List<Book> books = bookService.findByAuthor(author);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{bookId}/assign/{userId}")
    public ResponseEntity<Book> assignBookToUser(@PathVariable Long bookId, @PathVariable Long userId) {
        Book updatedBook = bookService.assignToUser(bookId, userId);
        return ResponseEntity.ok(updatedBook);
    }
}