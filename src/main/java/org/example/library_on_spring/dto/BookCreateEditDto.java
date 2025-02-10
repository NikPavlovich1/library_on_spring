package org.example.library_on_spring.dto;

public record BookCreateEditDto(

        String title,

        String author,

        String category,

        Integer categoryOrder
    ) {
}
