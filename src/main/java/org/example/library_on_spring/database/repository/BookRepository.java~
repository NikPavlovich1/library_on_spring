package org.example.library_on_spring.database.repository;

import org.example.library_on_spring.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Репозиторий для книг
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory(String category);

    @Query("select max(b.categoryOrder) from Book b where b.category = :category")
    Long findMaxCategoryOrderByCategory(String category);

    List<Book> findByCategory(String category);
}
