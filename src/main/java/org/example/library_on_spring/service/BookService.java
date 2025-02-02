package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update(Long id, Book updatedBook) {
        Book existingBook = findById(id); // Находим книгу по ID
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setCategoryOrder(updatedBook.getCategoryOrder());
        existingBook.setUser(updatedBook.getUser());
        return bookRepository.save(existingBook); // Сохраняем обновленную книгу
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }
}
