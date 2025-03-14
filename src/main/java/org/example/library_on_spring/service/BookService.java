package org.example.library_on_spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.BookCreateEditDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(
            BookRepository bookRepository,
            UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга не найдена " + id));
    }

    public Book save(BookCreateEditDto bookDto){
        Book book = new Book();
        book.setTitle(bookDto.title());
        book.setAuthor(bookDto.author());
        book.setCategory(bookDto.category());
        book.setCategoryOrder(Long.valueOf(bookDto.categoryOrder()));
        return bookRepository.save(book);
    }

    public Book update(Long id, BookCreateEditDto bookUpdateDto) {
        Book existingBook = findById(id); // Находим книгу по ID
        existingBook.setTitle(bookUpdateDto.title());
        existingBook.setAuthor(bookUpdateDto.author());
        existingBook.setCategory(bookUpdateDto.category());
        existingBook.setCategoryOrder(Long.valueOf(bookUpdateDto.categoryOrder()));
        return bookRepository.save(existingBook); // Сохраняем обновленную книгу
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book assignToUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        book.setUser(user);
        return bookRepository.save(book);
    }

}
