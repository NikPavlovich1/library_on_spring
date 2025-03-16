package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.Transaction;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.database.repository.TransactionRepository;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.BookReadDto;
import org.example.library_on_spring.exception.BookNotFoundException;
import org.example.library_on_spring.exception.UserNotFoundException;
import org.example.library_on_spring.mapper.BookReadMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookService bookService;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookReadMapper bookReadMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              BookService bookService,
                              UserService userService,
                              BookRepository bookRepository,
                              UserRepository userRepository,
                              BookReadMapper bookReadMapper) {
        this.transactionRepository = transactionRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookReadMapper = bookReadMapper;
    }


    @Transactional
    public Transaction issueBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + userId + " не найден"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Книга с ID " + bookId + " не найдена"));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Книга с ID " + bookId + " недоступна для выдачи");
        }
        Optional<Transaction> activeTransaction = transactionRepository
                .findByBookAndReturnDateIsNull(book);
        if (activeTransaction.isPresent()) {
            if (activeTransaction.get().getUser().getId().equals(userId)) {
                throw new RuntimeException("Эта книга уже у вас на руках, сначала верните её");
            } else {
                throw new RuntimeException("Эта книга уже выдана другому пользователю");
            }
        }
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setIssueDate(LocalDateTime.now());
        book.setUser(user);
        bookRepository.save(book);
        return transactionRepository.save(transaction);
    }


    @Transactional
    public Transaction returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Книга с ID " + bookId + " не найдена"));

        Optional<Transaction> activeTransaction = transactionRepository
                .findByBookAndReturnDateIsNull(book);
        if (!activeTransaction.isPresent()) {
            throw new RuntimeException("Эта книга не была выдана и не может быть возвращена");
        }
        Transaction transaction = transactionRepository.findByBookAndReturnDateIsNull(book)
                .orElseThrow(() -> new IllegalStateException("Активная транзакция для книги с ID " + bookId + " не найдена"));
        book.setAvailable(true);
        bookRepository.save(book);
        transaction.setReturnDate(LocalDate.now().atStartOfDay());
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getUserTransactions(Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Пользователь с ID " + userId + " не найден");
        }
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getBookTransactions(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Книга с ID " + bookId + " не найдена");
        }
        List<Transaction> transactions = transactionRepository.findByBookId(bookId);
        return ResponseEntity.ok(transactions);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getIssuedBooks() {
        List<Book> issuedBooks = transactionRepository.findIssuedBooks();
        if (issuedBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ни одна книга не выдана");
        }
        List<BookReadDto> issuedBooksDto = issuedBooks.stream()
                .map(bookReadMapper::map)
                .toList();
        return ResponseEntity.ok(issuedBooksDto);
    }

}