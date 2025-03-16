package org.example.library_on_spring.http.controller;

import jakarta.validation.groups.Default;
import org.example.library_on_spring.database.entity.Transaction;
import org.example.library_on_spring.exception.BookNotFoundException;
import org.example.library_on_spring.exception.UserNotFoundException;
import org.example.library_on_spring.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<Transaction> issueBook(
            @RequestParam @Validated({Default.class}) Long userId,
            @RequestParam @Validated({Default.class}) Long bookId) {
        Transaction transaction = transactionService.issueBook(userId, bookId);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PostMapping("/return")
    public ResponseEntity<Transaction> returnBook(
            @RequestParam @Validated({Default.class}) Long bookId) {
        Transaction transaction = transactionService.returnBook(bookId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/users/{userId}/transactions")
    public ResponseEntity<?> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getUserTransactions(userId);
    }

    @GetMapping("/books/{bookId}/transactions")
    public ResponseEntity<?> getBookTransactions(@PathVariable Long bookId) {
        return transactionService.getBookTransactions(bookId);
    }

    @GetMapping("/issued")
    public ResponseEntity<?> getIssuedBooks() {
        return transactionService.getIssuedBooks();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}