package org.example.library_on_spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.BookRepository;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.validation.UniqueUserName;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final BookRepository bookRepository;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto fromObject) {
        User user = new User();
        copy(fromObject, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setSurname(object.getSurname());
        user.setCreatedAt(object.getCreatedAt());
        Book book = getBook(object.getBookId());
        if (book != null) {
            user.getBook().add(book);
        }
    }

    private Book getBook(Long bookId){
        return Optional.ofNullable(bookId)
                .flatMap(bookRepository::findById)
                .orElse(null);
    }
}
