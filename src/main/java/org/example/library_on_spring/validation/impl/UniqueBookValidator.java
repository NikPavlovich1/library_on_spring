package org.example.library_on_spring.validation.impl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.dto.BookCreateEditDto;
import org.example.library_on_spring.validation.UniqueBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueBookValidator implements ConstraintValidator<UniqueBook, BookCreateEditDto> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(BookCreateEditDto bookDto, ConstraintValidatorContext context) {
        if (bookDto == null) {
            return true;
        }
        boolean exists = bookRepository.existsByTitleAndAuthor(
                bookDto.title(),
                bookDto.author()
        );
        return !exists;
    }
}
