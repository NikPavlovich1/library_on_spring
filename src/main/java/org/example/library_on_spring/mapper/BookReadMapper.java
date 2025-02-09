package org.example.library_on_spring.mapper;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.dto.BookReadDto;
import org.example.library_on_spring.dto.UserReadDto;
import org.example.library_on_spring.dto.UserShortReadDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookReadMapper implements Mapper<Book, BookReadDto> {
    @Override
    public BookReadDto map(Book object) {
        return new BookReadDto(
                object.getId(),
                object.getTitle(),
                object.getAuthor(),
                object.getCategory(),
                object.getCompositeKey(),
                object.getTransactions().isEmpty() ? null : new UserShortReadDto(
                        object.getTransactions().get(0).getUser().getId(),
                        object.getTransactions().get(0).getUser().getFirstname(),
                        object.getTransactions().get(0).getUser().getLastname(),
                        object.getTransactions().get(0).getUser().getSurname()
                )
        );
    }
}
