package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.dto.BookCreateEditDto;
import org.example.library_on_spring.dto.BookUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(
            BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга не найдена " + id));
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update(Long id, BookUpdateDto bookUpdateDto) {
        Book existingBook = findById(id); // Находим книгу по ID
        existingBook.setTitle(bookUpdateDto.getTitle());
        existingBook.setAuthor(bookUpdateDto.getAuthor());
        existingBook.setCategory(bookUpdateDto.getCategory());
        existingBook.setCategoryOrder(Long.valueOf(bookUpdateDto.getCategoryOrder()));
        return bookRepository.save(existingBook); // Сохраняем обновленную книгу
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

}
