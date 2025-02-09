package org.example.library_on_spring.dto;

public record BookReadDto(Long id,
                            String title,
                            String author,
                            String category,
                            String compositeKey,
                            UserShortReadDto user) {

}
