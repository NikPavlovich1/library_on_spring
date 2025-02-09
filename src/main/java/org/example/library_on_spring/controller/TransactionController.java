package org.example.library_on_spring.controller;

import org.example.library_on_spring.database.entity.Transaction;
import org.example.library_on_spring.dto.BookReadDto;
import org.example.library_on_spring.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<Transaction> issueBook(@RequestParam Long userId, @RequestParam Long bookId) {
        Transaction transaction = transactionService.issueBook(userId, bookId);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PostMapping("/return")
    public ResponseEntity<Transaction> returnBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        Transaction transaction = transactionService.returnBook(userId, bookId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getUserTransactions(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Transaction>> getBookTransactions(@PathVariable Long bookId) {
        List<Transaction> transactions = transactionService.getBookTransactions(bookId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/issued")
    public List<BookReadDto> getIssuedBooks() {
        return transactionService.getIssuedBooks();
    }
}