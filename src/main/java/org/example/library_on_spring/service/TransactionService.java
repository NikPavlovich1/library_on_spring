package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.Transaction;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.database.repository.TransactionRepository;
import org.example.library_on_spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TransactionService(TransactionRepository transactionRepository,
                              BookService bookService,
                              UserService userService,
                              BookRepository bookRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Transaction issueBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));

        // Проверяем, есть ли уже активная выдача этой книги
        Optional<Transaction> activeTransaction = transactionRepository
                .findByBookAndReturnDateIsNull(book);

        if (activeTransaction.isPresent()) {
            // Если книга уже у того же пользователя
            if (activeTransaction.get().getUser().getId().equals(userId)) {
                throw new RuntimeException("Эта книга уже у вас на руках, сначала верните её");
            } else {
                throw new RuntimeException("Эта книга уже выдана другому пользователю");
            }
        }

        // Если книга свободна, создаём новую транзакцию
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setIssueDate(LocalDateTime.now());

        book.setUser(user); // Назначаем владельца книги
        bookRepository.save(book);

        return transactionRepository.save(transaction);
    }


    @Transactional
    public Transaction returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));

        // Получаем активную транзакцию (если она есть)
        Optional<Transaction> activeTransaction = transactionRepository
                .findByBookAndReturnDateIsNull(book);

        if (!activeTransaction.isPresent()) {
            throw new RuntimeException("Эта книга не была выдана и не может быть возвращена");
        }

        // Получаем транзакцию
        Transaction transaction = activeTransaction.get();

        // Проверяем, что книга была выдана этому пользователю
        if (!transaction.getUser().getId().equals(userId)) {
            throw new RuntimeException("Эта книга была выдана другому пользователю");
        }

        // Завершаем транзакцию, ставим дату возврата
        transaction.setReturnDate(LocalDateTime.now());

        // Снимаем владельца книги
        book.setUser(null);
        bookRepository.save(book);

        return transactionRepository.save(transaction);
    }



    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getBookTransactions(Long bookId) {
        return transactionRepository.findByBookId(bookId);
    }



}