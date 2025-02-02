package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.exception.BookOrderException;
import org.example.library_on_spring.database.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// Сервис для управления порядковыми номерами книг
@Service
public class BookOrderService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Long assignCategoryOrder(String category) {
        Long maxCategoryOrder = bookRepository.findMaxCategoryOrderByCategory(category);
        if (maxCategoryOrder == null) {
            return 1L;
        } else {
            return maxCategoryOrder + 1;
        }
    }

    @Transactional
    public void releaseCategoryOrder(String category, Long removedOrder) {
        List<Book> books = bookRepository.findAllByCategory(category);
        if (books.isEmpty()) {
            return;
        }
        adjustCategoryOrders(books, removedOrder);
        try {
            bookRepository.saveAll(books);
        } catch (Exception e) {
            throw new BookOrderException("Ошибка при сохранении книги", e);
        }
    }

    private void adjustCategoryOrders(List<Book> books, Long removedOrder) {
        for (Book book : books) {
            if (book.getCategoryOrder() > removedOrder) {
                book.setCategoryOrder(book.getCategoryOrder() - 1);
            }
        }
    }
}
