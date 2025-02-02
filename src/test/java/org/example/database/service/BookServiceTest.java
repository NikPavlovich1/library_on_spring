package org.example.database.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testFindById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book result = bookService.findById(1L);
        assertEquals("Effective Java", result.getTitle());
    }

    @Test
    public void testSave() {
        Book book = new Book();
        book.setTitle("Clean Code");
        when(bookRepository.save(book)).thenReturn(book);
        Book result = bookService.save(book);
        assertEquals("Clean Code", result.getTitle());
    }
}