package org.example.library_on_spring.mapper;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.dto.BookReadDto;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookReadMapper implements Mapper<Book, BookReadDto> {
    @Override
    public BookReadDto map(Book object) {
       return new BookReadDto(
               object.getId(),
               object.getTitle()
       );
    }
}
