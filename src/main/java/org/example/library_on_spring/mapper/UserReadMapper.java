package org.example.library_on_spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.dto.BookReadDto;
import org.example.library_on_spring.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final BookReadMapper bookReadMapper;


    @Override
    public UserReadDto map(User object) {
        List<BookReadDto> bookReadDtos = object.getBook().stream()
                .map(bookReadMapper::map)
                .toList();
        return new UserReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getSurname(),
                object.getCreatedAt(),
                bookReadDtos
        );
    }
}
