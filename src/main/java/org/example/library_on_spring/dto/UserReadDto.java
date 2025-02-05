package org.example.library_on_spring.dto;

import lombok.Value;
import java.time.LocalDate;
import java.util.List;

@Value
public class UserReadDto {
    Long id;
    String firstname;
    String lastname;
    String surname;
    LocalDate createdAt;
    List<BookReadDto> book;
}
