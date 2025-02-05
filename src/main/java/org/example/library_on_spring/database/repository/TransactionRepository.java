package org.example.library_on_spring.database.repository;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByBookId(Long bookId);
    boolean existsByBookAndReturnDateIsNull(Book book);

    Optional<Transaction> findActiveTransactionByBook(Book book);

    Optional<Transaction> findByBookAndReturnDateIsNull(Book book);
}